package cache;

import java.util.Objects;

public class Box implements Comparable<Box> {
    private int content;
    private boolean refBit;                                     // false for 0, true for 1
    private long lastUpdated = System.nanoTime();               // in nanoseconds

    public Box(int content) {
        this.content = content;
    }

    public int getContent() {
        return content;
    }
    public void setContent(int content) {
        this.content = content;
        this.lastUpdated = System.nanoTime();
    }
    public boolean isRefBit() {
        return refBit;
    }
    public void setRefBit(boolean refBit) {
        this.refBit = refBit;
        this.lastUpdated = System.nanoTime();
    }
    public long getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return content == box.content;
    }
    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
    @Override
    public String toString() {
        return "" + content;
    }

    @Override
    public int compareTo(Box that) {
        if (this == that)
            return 0;
        else if (this.refBit && !that.refBit)
            return 1;
        else if (!this.refBit && that.refBit)
            return -1;
        else
            return (this.lastUpdated > that.getLastUpdated() ? 1 : -1);
    }
}