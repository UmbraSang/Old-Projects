public class Maze{
	
	String[] textmaze;
	char[][] maze;
	CellDeque path=new CellDeque();
	Cell start;
	Cell finish;
	
	public Maze(java.lang.String[] textmaze, Cell start, Cell finish){
		this.textmaze=textmaze;
		this.start=start;
		this.finish=finish;
	}
	
	/*
	Called by RunnSolver to print maze
	and build a list of coordinates for
	the path through it.
	*/
	public CellDeque solve(){
		charBuilder();
		System.out.println("\nPre Nav");
		System.out.print(toString());
		boolean route = findPath(start, finish);
		if(route==false){
			System.out.println("\nThere is no route from Start to Exit");
			path.makeEmpty();
		}
		System.out.println("\nPost Nav");
		System.out.println(toString());
		return path;
		
	}
	
	/*
	Called by solve to build our
	2D char array from our 1D textmaze.
	Also shows the user what the
	maze looks like.
	*/
	public void charBuilder(){
		this.maze = new char[textmaze.length][textmaze[0].length()];
		
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[0].length;j++){
				maze[i][j]=textmaze[i].charAt(j);
			}
		}
	}
	
	/*
	Called from solver to actually solve the maze.
	uses recursion to build the deque that shows
	our path through the maze.
	*/
	private boolean findPath(Cell src, Cell dest){
		boolean pathFound=false;
		boolean up, down, right, left = false;
		path.insertLast(src);
		
		if(src.equals(dest)){
			maze[src.row][src.col]='V';
			return true;
		}else if(src.row>this.maze.length||src.col>this.maze[0].length||src.row<0||src.col<0||maze[src.row][src.col]=='*'||maze[src.row][src.col]=='V'){
			if(!src.equals(src)){
				path.removeLast();
			}
			return false;
		}else{
			maze[src.row][src.col]='V';
			pathFound=findPath(new Cell(src.row+1,src.col), dest);
				if(pathFound==true){
					return true;
				}else{
					down=true;
				}
			pathFound=findPath(new Cell(src.row,src.col+1), dest);
				if(pathFound==true){
					return true;
				}else{
					right=true;
				}
			pathFound=findPath(new Cell(src.row-1,src.col), dest);
				if(pathFound==true){
					return true;
				}else{
					up=true;
				}
			pathFound=findPath(new Cell(src.row,src.col-1), dest);
				if(pathFound==true){
					return true;
				}else{
					left=true;
				}
			if(up==true&&right==true&&up==true&&left==true){
				path.removeLast();
			}
			return false;
		}
	}
	/*
	Uses out 2D char array to
	build a String that is a
	meaningful representation
	of the maze.
	*/
	public String toString(){
		String Visual="";
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[0].length;j++){
				Visual += maze[i][j];
			}
			Visual+="\n";
		}
		return Visual;
	}
}