package cover;

import java.util.ArrayList;

public class SetABC {
    private ArrayList<Component> components;

    public SetABC() {
        this.components = new ArrayList<>();
    }

    public void addComponent(Component c) {
        components.add(c);
    }

    public int coverIntersection(SetZ Z) {
        int count = 0;
        for (int i = 0; i < components.size(); i++) {
            count += components.get(i).coverIntersection(Z);
        }
        return count;
    }
}
