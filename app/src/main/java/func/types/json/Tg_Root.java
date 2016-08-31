package func.types.json;

import java.util.List;

/**
 * author-ZKC
 * create on 2016-08-31-10-47.
 */
public class Tg_Root {
    private boolean status;
    private List<Tg> tgs;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Tg> getTgs() {
        return tgs;
    }

    public void setTgs(List<Tg> tgs) {
        this.tgs = tgs;
    }
}
