package dxzmpk.model;

public class Shift extends Action{

    Integer toState;

    @Override
    public int getInt() {
        return toState;
    }

    public Shift(Integer toState) {
        this.toState = toState;
    }
}
