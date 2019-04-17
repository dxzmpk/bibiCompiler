package syntacticAnalyzer.interfaces;

import syntacticAnalyzer.model.Closure;

import java.util.List;

public interface AnalyzerInterface {

    /**
     * 根据文件名构造得出原始产生式集合，存在closure中
     * @param fileName
     * @return
     */
    public Closure initClosure(String fileName);

    /**
     * Closure函数，读入原始的产生式生成闭包。
     * @param initialClosure 原始的产生式由initClosure从文件中读取并基本处理获得
     * @return
     */
    public Closure getClosure(Closure initialClosure);

    /**
     * GOTO函数
     * @param closureName clousure的名称以及看到的输入
     * @param inputX
     * @return
     */
    public Closure getGoto(String closureName, String inputX);

}
