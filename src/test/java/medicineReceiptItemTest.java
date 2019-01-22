import org.junit.Test;
import sample.medicineReceiptItem;

import static org.junit.Assert.assertEquals;

public class medicineReceiptItemTest {

    @Test
    public void testName(){
        medicineReceiptItem item = new medicineReceiptItem("Aspirin", "120mg", "12");
        String outName = item.getName();

        assertEquals("Aspirin",outName);
    }
    @Test
    public void testDosage(){
        medicineReceiptItem item = new medicineReceiptItem("Aspirin", "120mg", "12");
        String outDosage = item.getDosage();


        assertEquals("120mg",outDosage);
    }
    @Test
    public void testDays(){
        medicineReceiptItem item = new medicineReceiptItem("Aspirin", "120mg", "12");
        String outDays = item.getDays();

        assertEquals("12",outDays);
    }
}
