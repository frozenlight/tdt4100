package objects;

import java.util.*;

public class Person {
	
	private String name;
	private Person spouse;
	
	private List<Person> friends = new ArrayList<Person>();
	private List<Dog> dogs = new ArrayList<Dog>();
	
	public Person(String name) {
		this.name = name;
	}
	
	public void addDog(Dog dog) {
		dogs.add(dog);
		dog.setOwner(this);
	}
	
	public void removeDog(Dog dog) {
	}
	
	public void addFriend(Person person) {
		if (friends.contains(person)) return;
		friends.add(person);
		person.friends.add(this);
	}
	
	public void marry(Person person) {
		this.spouse = person;
		person.spouse = this;
	}
	
	public void divorce() {
		spouse.spouse = null;
		this.spouse = null;
	}
	
	@Override
	public String toString() {
		if (this.spouse != null) {
			return name + ", gift med " + spouse.name;
		}
		return name;
	}
	
	public static void main(String[] args) {
		Person per = new Person("Per");
		Person anne = new Person("Anne");
		Person tom = new Person("Tom");
		per.addFriend(tom);
		per.marry(anne);
		
		
	}
}
