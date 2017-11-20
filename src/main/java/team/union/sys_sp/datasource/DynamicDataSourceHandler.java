package team.union.sys_sp.datasource;
public class DynamicDataSourceHandler {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();
    /**
     * 设置数据源类型 
     * @param name 数据库类型 
     */
    public static void putDataSource(String name) {
        holder.set(name);
    }
    /**
     * 获取数据源类型
     * @return
     */
    public static String getDataSouce() {
        return holder.get();
    }
    /**
     * 清除数据源类型
     
    public static void clearDataSource() {  
        holder.remove();  
    }  
    */
}