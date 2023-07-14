package hello.core.singleton;

import java.sql.SQLOutput;

public class SingletonService {
	//static 영역에 객체를 딱 1개만 생성해둔다 실행될때 하나만 생성해서 인스턴스에 넣어둔다
	private static final SingletonService instance = new SingletonService();
	//조회할때는 밑에서 호출하면 된다
	public static SingletonService getInstance() {
		return instance;
	}
	//private 생성자로 밖에서 new로 생성하는것을 막아준다 정말 중요한것임
	private SingletonService() {

	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}

}
