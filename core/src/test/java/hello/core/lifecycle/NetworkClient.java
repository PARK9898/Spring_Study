package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
	private String url;

	public NetworkClient() {
		System.out.println("생성자 호출, url = " + url);
	}

	public void seturl(String url) {
		this.url = url;
	}
	//서비스 시작 시 호출
	public void connect() {
		System.out.println("connect: " +url);
	}
	public void call(String message) {
		System.out.println("call: " + url + "message = " + message);
	}

	//서비스 종료시 혹출
	public void disconnect() {
		System.out.println("close: " + url);
	}

	//@Override //의존관계 주입이 끝나면 호출해주겠다 초기화빈에 있는 메서드임
	@PostConstruct
	public void init() {
		System.out.println("NetworkClient.init");
		connect();
		call("초기화 연결 매시지");
	}

	@PreDestroy
	public void close() {
		System.out.println("NetWorkClient.close");
		disconnect();
	}
}
