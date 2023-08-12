package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter // lombok 사용시 getter setter 사용 가능
@ToString // 알아보기
public class HelloLombok {

	private String name;
	private int age;

	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok();
		helloLombok.setName("sdjsa");

		String name = helloLombok.getName();
		System.out.println("name = " + helloLombok);
	}
}
