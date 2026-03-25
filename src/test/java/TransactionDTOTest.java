

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avega.tax_compliance.dto.TransactionDTO;
import com.avega.tax_compliance.entity.TransactionType;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // This sets up the validation engine just like Spring Boot does behind the scenes
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRefundTransaction_ShouldPass() {
        // 1. Arrange: Create a DTO with a negative amount (Refund)
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId("REFUND-123");
        dto.setCustomerId("CUST-99");
        dto.setAmount(new BigDecimal("-5000.00"));
        dto.setTaxRate(new BigDecimal("0.18"));
        dto.setReportedTax(new BigDecimal("-900.00"));
        dto.setTransactionType(TransactionType.REFUND); // ✅ Accepts the Enum        dto.setDate(LocalDate.of(2026, 3, 25));

        // 2. Act: Validate the DTO
        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(dto);

        // 3. Assert: There should be ZERO errors because we allow negative numbers
        assertTrue(violations.isEmpty(), "A valid refund should have no validation errors");
    }

    @Test
    void testInvalidTransaction_MissingAmount_ShouldFail() {
        // 1. Arrange: Create a DTO but FORGET to set the amount
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId("TXN-124");
        // amount is intentionally left null
        dto.setTaxRate(new BigDecimal("0.18")); 

        // 2. Act: Validate the DTO
        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(dto);

        // 3. Assert: There should be exactly ONE error (the @NotNull on amount)
        assertEquals(1, violations.size());
        
        // Optional: Verify the exact error message
        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Amount is required", violation.getMessage()); // Update this to match your exact DTO message
    }
}