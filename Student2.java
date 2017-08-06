package cn.student.suchengjiang;

import java.util.Scanner;

class STU {
	String name;
	int num;
	double score[] = new double  [10];
	double sum = 0.0;
	double average=0.0;
	STU(String name, int num) {
		this.name = name;
		this.num = num;
		for (int i = 0; i <10; i++)
			score[i] = 0;
	}
	void setscore() {
		System.out.println("请输入十门课成绩：");
		for (int i = 0; i < 10; i++) {
			Scanner input = new Scanner(System.in);
			score[i] = input.nextInt();
	}
	}
	double getsum() {
		for (int i = 0; i < 10; i++)
			sum += score[i];
		return sum;
	}
	double getaver() {
		average = sum / 10;
		return average;
	}
	void display() {
		System.out.println("学号：" + num);
		System.out.println("姓名：" + name);
		System.out.println("十门课分数：");
		for (int i = 0; i < 10; i++)
		System.out.print(score[i] + "  ");
		System.out.println("\n");
		System.out.println("总分：" + getsum());
		System.out.println("平均分：" + getaver());
	}
}
public class Student2 {
	public static void main(String args[]) {
		STU stu = new STU("张三", 1001);
		stu.setscore();
		stu.display();
		}

}
