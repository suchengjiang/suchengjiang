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
		System.out.println("������ʮ�ſγɼ���");
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
		System.out.println("ѧ�ţ�" + num);
		System.out.println("������" + name);
		System.out.println("ʮ�ſη�����");
		for (int i = 0; i < 10; i++)
		System.out.print(score[i] + "  ");
		System.out.println("\n");
		System.out.println("�ܷ֣�" + getsum());
		System.out.println("ƽ���֣�" + getaver());
	}
}
public class Student2 {
	public static void main(String args[]) {
		STU stu = new STU("����", 1001);
		stu.setscore();
		stu.display();
		}

}
