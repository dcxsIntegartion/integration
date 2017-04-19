package team.union.basic_data.com.rs;

public class RequestPage {
	private int pageSize;

    private boolean isAll;

    private int curPage;
    
    public RequestPage(){}

    public RequestPage(int totalRows, int curPage) {
    	if(totalRows<0){
    		totalRows=10;
    	}
    	if(curPage<0){
    		curPage=1;
    	}
        this.pageSize = totalRows;
        this.curPage = curPage;
        
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setIsAll(boolean isAll) {
        this.isAll = isAll;
    }
}
