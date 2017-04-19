package team.union.nonbusiness.timer.service;
import org.springframework.stereotype.Repository;
@Repository
public class ITimerServiceImpl{
	public synchronized void test() {
		System.out.println("定时器");
	}
}
