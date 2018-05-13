package spotinstTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Node<T> implements Iterable<Node<T>> {
	private T data;
	private Node<T> parent;
	private List<Node<T>> child;
	private List<Node<T>> elementsIndex;
	
	public T getData() {
		return data;
	}

	public List<Node<T>> getChild() {
		return child;
	}

	public Node(T data) {
		this.data = data;
		child = new ArrayList<Node<T>>();

		this.elementsIndex = new LinkedList<Node<T>>();
		this.elementsIndex.add(this);
	}

	public Node<T> addChild(T child) {
		Node<T> childNode = new Node<T>(child);
		childNode.parent = this;
		this.child.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}
	
	public boolean removeChild(Node<T> childToRemove){
		return child.remove(childToRemove) && elementsIndex.remove(childToRemove);
	}
	
	public int getLevel() {
		return parent == null ? 0 : parent.getLevel()+1;
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "Empty Data";
	}

	@Override
	public Iterator<Node<T>> iterator() {
		NodeIterator<T> iter = new NodeIterator<T>(this);
		return iter;
	}

	private void registerChildForSearch(Node<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public Node<T> findNode(Comparable<T> cmp) {
		for (Node<T> element : this.elementsIndex) {
			T elData = element.data;
			if (cmp.compareTo(elData) == 0)
				return element;
		}
		return null;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
}
