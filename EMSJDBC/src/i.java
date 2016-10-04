import java.util.concurrent.ThreadLocalRandom;

interface ip {

	default void show(){
		System.out.println("from interface");
	}
}


class test implements ip{
@Override
	public void show() {
		ip.super.show();
		System.out.println("from class");
	}	
}


class i{
	public static void main(String[] args) {
		test i = new test();
		i.show();
		Thread t = new Thread();
		t.start();
		
	}
}