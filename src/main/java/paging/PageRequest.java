package paging;

public class PageRequest implements PageAble {

    private int page;
    private int size;

    @Override
    public int getObset() {

        return (page-1)*size;
    }

    @Override
    public int getLimit() {

        return size;

    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }
}
