package hello.core.singleton;

public class SingletonService {
	//static 영역에 하나만 올라감
	private static final SingletonService instance = new SingletonService();

	public static SingletonService getInstance() {
		return instance;
	}

}
