package test.syntacticAnalyzer;

import org.junit.Test;
import syntacticAnalyzer.implement.InitClousereAndGoto;
import syntacticAnalyzer.model.Closure;
import test.BaseTest;

public class InitClousereAndGotoTest extends BaseTest {

    @Test
    public void getClousureTest(){
        InitClousereAndGoto initClousereAndGoto = new InitClousereAndGoto();
        initClousereAndGoto.getClousure();
    }

}
