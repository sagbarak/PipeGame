package server;

public interface CacheManager<T> {
	public void writeCM(); //write cache manager to file
	public void readCM(); //read cache manager from file
	public boolean isExist(T problem); //return true if a solution found for a problem
	public T getSolutioncm(T problem); // return solution for a problem
	public void addSolution(T solution,T problem); //add new solution to cache manager
}
