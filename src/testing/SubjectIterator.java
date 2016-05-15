package testing;

import java.util.Iterator;
import java.util.List;

public class SubjectIterator implements Iterator<Subject>{
	
	private List<Subject> list;
	private int position;
	public SubjectIterator(List<Subject> list) {
		position = list.size()-1;
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return position >= 0;
	}

	@Override
	public Subject next() {
		// TODO Auto-generated method stub
		return list.get(position--);
	}
	

}
