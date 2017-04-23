package team.union.nonbusiness.timer.service;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;
@Repository
public class ITimerServiceImpl{
	public synchronized void test() {
		System.out.println("定时器");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
