
import org.junit.Test;
import sample.dataModel;
import sample.medicineReceiptItem;

import static org.junit.Assert.assertEquals;

public class dataModelTest {

    dataModel dm = new dataModel();

    @Test
    public void testConnection(){


        String response = dm.connectDataBase();
        assertEquals("Succesfully Connected",response);
    }
    @Test
    public void testInsertData(){
        String tableName = "medicinereciepts";
        String[] keys = {"medicineReceiptId","medicineDescriptions"};
        String[] values = {"abcd1234","adjasdjlksajdlksajdsjadknsadjnsakjdasjd"};
        dm.connectDataBase();
        String response = dm.insertData(tableName,keys,values);
        assertEquals("Succesfully inserted",response);
    }
    @Test
    public void testcloseConnection(){
        dm.connectDataBase();
        String response = dm.closeConnection();

        assertEquals("connection terminated succesfully",response);
    }
}
