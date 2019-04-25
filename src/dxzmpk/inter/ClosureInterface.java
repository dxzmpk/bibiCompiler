package dxzmpk.inter;

import dxzmpk.impl.Closure;
import dxzmpk.impl.Item;
import dxzmpk.impl.Production;

import java.util.List;

public interface ClosureInterface {

    /**
     * 从初始项集构造得出闭包
     * @param item
     * @return
     */
    public Closure closureBuilder(Item item, List<Production> productions);


    /**
     * 计算goto函数，数学表示为GOTO(I,X)，I是项集(这里用Closure存储)，X是文法符号
     * @param closure,symbol
     * @return
     */
    public Closure gotoFunction(Closure closure, String symbol, List<Production> productions);

}
