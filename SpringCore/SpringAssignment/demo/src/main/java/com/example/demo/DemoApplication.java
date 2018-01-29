package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Customer {

	private Person person;
	private String action;
	private int type;

	public Person getPerson() {
		return person;
	}

    @Autowired
	public void setPerson(Person person) {
		this.person = person;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

    StringBuilder getInfo() {
	    StringBuilder builder = new StringBuilder();
        builder.append("Getting Customer Data......\n").append("Customer : \n");
	    builder.append("\tAction = " + this.getAction()).append(", ");
	    builder.append("\tType = " + this.getType()).append("\n");

	    Person person = this.getPerson();

	    builder.append("\tPerson : \n");
	    builder.append("\tName = ").append(person.getName()).append(", ");
	    builder.append("\tAddress = ").append(person.getAddress()).append(", ");
	    builder.append("\tAge = ").append(person.getAge());

	    return builder;
    }
}
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context =
				new ClassPathXmlApplicationContext("SpringBeans.xml");

		Customer customer = (Customer) context.getBean("customerBean");

		System.out.print(customer.getInfo());
	}
}
