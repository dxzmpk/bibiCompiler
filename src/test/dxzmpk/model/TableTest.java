package test.dxzmpk.model;

import com.google.common.collect.Table;
import dxzmpk.impl.TableGenerater;
import org.junit.Test;
import test.BaseTest;

public class TableTest extends BaseTest {

    @Test
    public void generateTableTest() {
        Table table = TableGenerater.generateTable();
        System.out.println(table.toString());
    }
}
