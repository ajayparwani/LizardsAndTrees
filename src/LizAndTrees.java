import java.util.ArrayList;
import java.io.*;
import java.io.IOException;

import java.util.Random;


@SuppressWarnings("Duplicates")



public class LizAndTrees {

    boolean issafe=true;
    static Position[] correctSolution;
    static ArrayList<Position> allt = new ArrayList<Position>();
    static Position[] currentState ;
    static Random ran = new Random();
    static long start;
    static long end = 275000;
    static Position[] lizards;
    static ArrayList<Position> tempdfs=new ArrayList<Position>();

    static class Position {
        int row, col;


        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public boolean ZooSolution(int n,int t,ArrayList<Position> allt) {
        ArrayList<Position > positions= new ArrayList<Position>();







            boolean hasSolution= dfssol(allt,positions,n,0,0,t);
            if (hasSolution) {
                tempdfs= positions;
                return true;
            }
        else
            {
            return false;
        }
    }
    public boolean bfsSolution(int n, int t, ArrayList<Position> allt) {
        ArrayList<Position[]> queue= new ArrayList<Position[]>();
        if( bfssolver(allt,queue,n, 0,0, t))
        {
            System.out.println("Solution found");
            return true;
        }
        else{
            return false;

        }

    }


    static boolean containstree(ArrayList<Position> allt, int row, int col){
        for(int e=0;e<allt.size();e++)
        {
            if(row==allt.get(e).row&&col==allt.get(e).col)
            {
                return true;

            }
        }
        return false;
    }
    int containsrow (ArrayList<Position> allt,int row ,int col){
        int maxcol=-1;
        for(int e=0;e<allt.size();e++)
        {
            if(allt.get(e).row==row && allt.get(e).col<col)
            {
                maxcol=allt.get(e).col;
            }
        }
        return maxcol;
    }
    int containscol (ArrayList<Position> allt,int row ,int col){
        int maxrowtree=-1;
        for(int e=0;e<allt.size();e++)
        {
            if(allt.get(e).col==col && allt.get(e).row<row)
            {
                maxrowtree=allt.get(e).row;
            }
        }
        return maxrowtree;
    }

    boolean checkver(ArrayList<Position> allt,ArrayList<Position> positions,int row , int col)
    {   int temp=containscol(allt,row,col);
        int maxrowqueen=-1;

        for(int p=0;p<positions.size() ;p++)
        {
            if(positions.get(p).col==col && positions.get(p).row<row)
            {
                maxrowqueen=positions.get(p).row;
            }
        }
        if(maxrowqueen<temp &&temp<row)
        {
            return true;
        }
        if(maxrowqueen==temp)
        {
            return true;
        }
        return false;
    }
    boolean checkhori(ArrayList<Position> allt,ArrayList<Position> positions,int row , int col )
    {   int temp=containsrow(allt,row,col);
        int maxcolqueen=-1;
        for(int p=0;p<positions.size();p++)
        {
            if(positions.get(p).row==row && positions.get(p).col<col)
            {
                maxcolqueen=positions.get(p).col;
            }
        }
        if(maxcolqueen<temp &&temp<col)
        {
            return true;
        }
        if(maxcolqueen==temp)
        {
            return true;
        }
        return false;
    }
    int containsdiag (ArrayList<Position> allt,int row ,int col){
        int maxdiagtree=-1;
        for(int e=0;e<allt.size();e++)
        {
            if(allt.get(e).col- allt.get(e).row== col-row && allt.get(e).col<col)
            {
                maxdiagtree=allt.get(e).col;
            }
        }
        return maxdiagtree;
    }


    boolean checkdiag(ArrayList<Position> allt,ArrayList<Position> positions,int row , int col )
    {   int temp=containsdiag(allt,row,col);
        int maxdiagqueen=-1;
        for(int p=0;p<positions.size();p++)
        {
            if(positions.get(p).col-positions.get(p).row == col-row && positions.get(p).col<col)
            {
                maxdiagqueen=positions.get(p).col;
            }
        }
        if(maxdiagqueen<temp &&temp<col)
        {
            return true;
        }
        if(maxdiagqueen==temp)
        {
            return true;
        }
        return false;
    }

    int containsantidiag (ArrayList<Position> allt,int row ,int col){
        int maxantidiagtree=-1;
        for(int e=0;e<allt.size();e++)
        {
            if(allt.get(e).col+allt.get(e).row== col+row && allt.get(e).col<col)
            {
                maxantidiagtree=allt.get(e).col;
            }
        }
        return maxantidiagtree;
    }


    boolean checkantidiag(ArrayList<Position> allt,ArrayList<Position> positions,int row , int col)
    {   int temp=containsantidiag(allt,row,col);
        int maxantidiagqueen=-1;
        for(int p=0;p<positions.size();p++)
        {
            if(positions.get(p).col+positions.get(p).row == col+row && positions.get(p).col<col)
            {
                maxantidiagqueen=positions.get(p).col;
            }
        }
        if(maxantidiagqueen<temp &&temp<col)
        {
            return true;
        }
        if(maxantidiagqueen==temp)
        {
            return true;
        }
        return false;
    }

    boolean checkverbfs(ArrayList<Position> allt,Position[] nodes,int row , int col)
    {   int temp=containscol(allt,row,col);
        int maxrowqueen=-1;

        for(int p=0;p<nodes.length ;p++)
        {
            if(nodes[p].col==col && nodes[p].row<row)
            {
                maxrowqueen=nodes[p].row;
            }
        }
        if(maxrowqueen<temp &&temp<row)
        {
            return true;
        }
        if(maxrowqueen==temp)
        {
            return true;
        }
        return false;
    }
    boolean checkhoribfs(ArrayList<Position> allt,Position[] nodes,int row , int col)
    {   int temp=containsrow(allt,row,col);
        int maxcolqueen=-1;
        for(int p=0;p<nodes.length;p++)
        {
            if(nodes[p].row==row && nodes[p].col<col)
            {
                maxcolqueen=nodes[p].col;
            }
        }
        if(maxcolqueen<temp &&temp<col)
        {
            return true;
        }
        if(maxcolqueen==temp)
        {
            return true;
        }
        return false;
    }
    boolean checkdiagbfs(ArrayList<Position> allt,Position[] nodes,int row , int col )
    {   int temp=containsdiag(allt,row,col);
        int maxdiagqueen=-1;
        for(int p=0;p<nodes.length;p++)
        {
            if(nodes[p].col-nodes[p].row == col-row && nodes[p].col<col)
            {
                maxdiagqueen=nodes[p].col;
            }
        }
        if(maxdiagqueen<temp &&temp<col)
        {
            return true;
        }
        if(maxdiagqueen==temp)
        {
            return true;
        }
        return false;
    }
    boolean checkantidiagbfs(ArrayList<Position> allt,Position[] nodes,int row , int col)
    {   int temp=containsantidiag(allt,row,col);
        int maxantidiagqueen=-1;
        for(int p=0;p<nodes.length;p++)
        {
            if(nodes[p].col+nodes[p].row == col+row && nodes[p].col<col)
            {
                maxantidiagqueen=nodes[p].col;
            }
        }
        if(maxantidiagqueen<temp &&temp<col)
        {
            return true;
        }
        if(maxantidiagqueen==temp)
        {
            return true;
        }
        return false;
    }








    boolean dfssol(ArrayList<Position> allt,ArrayList<Position> positions,int n,int  row,int col,int t)
    {
        if(t==0)
        {
            return true;
        }
        if(row==n)
        {
            row=0;
            col=col+1;
        }
        if(System.currentTimeMillis()-start> end){
            System.out.println("Time: "+ (System.currentTimeMillis()-start));
            return false;
        }
        if(col==n){
            return false;
        }
        //System.out.println("row: " + row + " col: " +col);
        //printArr(positions,n, allt);



        issafe = checkver(allt, positions, row, col) && (checkhori(allt, positions, row, col) &&
                (checkdiag(allt, positions, row, col)) && (checkantidiag(allt, positions, row, col)));



        if (containstree(allt, row, col)) {
            issafe = false;

        }


        if(issafe) {
            positions.add(new Position(row,col));



            if (dfssol(allt, positions, n, row + 1, col, t - 1))
            {
                return true;
            }
            else{
                positions.remove(positions.size()-1);
                return dfssol(allt,positions,n,row+1,col,t);
            }
        }



        else {


            return dfssol(allt, positions, n, row + 1, col, t);

        }
    }



    boolean bfssolver(ArrayList<Position> allt,ArrayList<Position[]> queue,int n,int  row,int col,int t) {

        Position[] nodes =new Position[1];
        Position[] curstate;
        nodes[0] = new Position(0,0);
        ArrayList<Position> queuePointer=new ArrayList<Position>();


        int t1=t;
        int count=0;
        int flag=0;

            /*
            queue.add(nodes);
            queuePointer.add(new Position(0,0));
*/
        queue.add(new Position[0]);
        queuePointer.add(new Position(-1,0));



        while (queue.size()!=0){


            curstate = queue.get(0);
            queue.remove(0);
            row=queuePointer.get(0).row;
            col=queuePointer.get(0).col;
            queuePointer.remove(0);

            if(curstate.length==t)
            {
                correctSolution = curstate;
                return true;
            }
            if(System.currentTimeMillis()-start> end){
                return false;
            }
            else if(row==n-1){
                row=-1;
                col++;
            }
            //System.out.print("\nRow: "+(row+1) + " Col: " + col);
            //printArr(curstate, n, allt);
            if(col>=n){
                // System.out.println("..........................................."+col);
            }else {
                issafe = checkverbfs(allt, curstate, row+1, col) && (checkhoribfs(allt, curstate, row+1, col) &&
                        (checkdiagbfs(allt, curstate, row+1, col)) && (checkantidiagbfs(allt, curstate, row+1, col)));

                if (containstree(allt, row+1, col)) {
                    issafe = false;
                }

                if (issafe) {
                    Position[] newArray = new Position[curstate.length+1];
                    newArray[newArray.length-1] = new Position(row+1,col);
                    System.arraycopy(curstate, 0, newArray, 0, curstate.length);
                    queue.add(newArray);
                    queuePointer.add(new Position(row+1, col));
                    //System.out.println("Added pointer ("+(row+1)+", " + col + ") to the pointers");
                }
                queuePointer.add(new Position(row+1, col));
                //System.out.println("Added pointer ("+(row+1)+", " + col + ") to the pointers");
                queue.add(curstate);

            }
        }


        System.out.println("Queue Empty");
        return false;
    }


    private static double schedule(double u){
        return 10/Math.log(1+u);
    }

    private static Position generateNextValidLocation(int n){
        int x = ran.nextInt(n);
        int y = ran.nextInt(n);
        if(containstree(allt,x,y)){
            return generateNextValidLocation(n);
        }else{
            boolean valid = true;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==x && tempLocation.col==y){
                    valid = false;
                    break;
                }
            }
            if(valid){
                return new Position(x,y);
            }else{
                return generateNextValidLocation(n);
            }
        }
    }

    private static int countConflicts(ArrayList<Position> allt,int x, int y, int n){
        int conflicts = 0;
        // above
        for(int i=x-1; i>=0; i--){
            if(containstree(allt,i,y))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==i && tempLocation.col==y){
                    conflicts++;
                }
            }
        }
        //below
        for(int i=x+1; i<n; i++){
            if(containstree(allt,i,y))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==i && tempLocation.col==y){
                    conflicts++;
                }
            }
        }
        //left
        for(int i=y-1; i>=0; i--){
            if(containstree(allt,x,i))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==x && tempLocation.col==i){
                    conflicts++;
                }
            }
        }
        //right
        for(int i=y+1; i<n; i++){
            if(containstree(allt,x,i))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==x && tempLocation.col==i){
                    conflicts++;
                }
            }
        }
        //top left
        for(int i=x-1, j=y-1; i>=0 && j>=0; i--, j--){
            if(containstree(allt,i,j))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==i && tempLocation.col==j){
                    conflicts++;
                }
            }
        }
        //top right
        for(int i=x-1, j=y+1; i>=0 && j<n; i--, j++){
            if(containstree(allt,i,j))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==i && tempLocation.col==j){
                    conflicts++;
                }
            }

        }
        //bottom right
        for(int i=x+1, j=y+1; i<n && j<n; i++, j++){
            if(containstree(allt,i,j))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==i && tempLocation.col==j){
                    conflicts++;
                }
            }
        }
        //bottom left
        for(int i=x+1, j=y-1; i<n && j>=0; i++, j--){
            if(containstree(allt,i,j))
                break;
            for (Position tempLocation : currentState) {
                if (tempLocation.row==i && tempLocation.col==j){
                    conflicts++;
                }
            }
        }
        return conflicts;
    }


    private static boolean SA(int k, int n, ArrayList<Position> allt) {

        Double Temperature;
        int randomQueenPosition;
        long deltaE;
        int newConflicts, oldConflicts;
        int stableState = 0;
        int nsquare = n*n;

        System.out.println("Trees: " + allt.size());
        if(allt.size() + k == nsquare){
            boolean solved = true;
            for(Position p : currentState){
                if(countConflicts(allt,p.row, p.col, n)!=0) {
                    solved = false;
                    break;
                }
            }
            if(solved){
                System.out.println("Breaking because no new place available");
                return true;
            }else{System.out.println("Breaking because no new place available  but no space");
                return false;

            }
        }


        for(Double u=2.0; u>0; u++){
            //System.out.print("t: " + t);
            Temperature = schedule(u);
            //System.out.print(" Temperature: " + Temperature);
            if(Temperature<0)
                return true;
            if(stableState==nsquare){
                boolean solved = true;
                for(Position p : currentState){
                    if(countConflicts(allt,p.row, p.col, n)!=0) {
                        solved = false;
                        break;
                    }
                }
                if(solved){
                    return true;
                }else{
                    //return true;
                    stableState=0;
                }
            }

            if(System.currentTimeMillis()-start> end){
                return false;
            }


            randomQueenPosition = ran.nextInt(k);
            //System.out.println(" Random Queen Position: " + randomQueenPosition);
            Position randomQueen = currentState[randomQueenPosition];
            oldConflicts = countConflicts(allt,randomQueen.row, randomQueen.col, n);
            //System.out.print("old conflicts:"+ oldConflicts);
            Position newPosition = generateNextValidLocation(n);
            newConflicts = countConflicts(allt,newPosition.row, newPosition.col, n);
            //System.out.print(" new conflicts:"+ newConflicts);
            deltaE = newConflicts - oldConflicts;
            //if (true) {
            if (oldConflicts!=0) {
                if(deltaE<=0) {
                    //System.out.println(" swapped because of lower value");
                    currentState[randomQueenPosition] = new Position(newPosition.row,newPosition.col);
                    stableState = 0;
                }else{
                    Double expDeltaETemp = Math.exp(-1*deltaE/Temperature);
                    Double mathRandom = Math.random();
                    if(mathRandom < expDeltaETemp){
                        //System.out.print(" exp: " + expDeltaETemp +" mathRandom: "+ mathRandom);
                        //System.out.println(" swapped because of probability");
                        currentState[randomQueenPosition] = new Position(newPosition.row,newPosition.col);
                        stableState=0;
                    }else{
                        stableState++;
                        //System.out.println();
                    }
                }
            }else{
                stableState++;
                //System.out.println();
            }
        }
        return true;
    }


    private static void printArr(ArrayList<Position>allt,int[][] arr, int n){
        try{
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.println("OK");


            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    //System.out.print("check:");

                    if (containstree(allt,i,j)) {
                        System.out.print("2");
                        writer.print("2");
                    } else {
                        System.out.print(arr[i][j]);
                        writer.print(arr[i][j]);
                    }
                }
                writer.println();
                System.out.println();
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    private static void printArrSA(ArrayList<Position>allt, Position[] list, int n) {
        int[][] atemp = new int[n][n];


        for (int i = 0; i < list.length; i++) {
            Position liz = list[i];
            atemp[liz.row][liz.col] = 1;
            //System.out.println("Row: " +liz.row + " col: " + liz.col);
        }
        System.out.println("Have "+ list.length + " lizards below" );
        printArr(allt,atemp,n);

    }
    private static void printFail(){
        try{
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.println("FAIL");
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private static void printArrbfs(Position[] positions, int n, ArrayList<Position> allt) {
        int [][] arr= new int [n][n];
        System.out.println("\nNumber of queens: " + positions.length);

        for(Position position : positions){
            // System.out.println("\npositions row: "+position.row + "positions col: "+position.col );
            arr[position.row][position.col] = 1;
        }

        for(Position alltr : allt){
            //System.out.println("\ntree row: "+alltr.row + "tree col: "+alltr.col );
            arr[alltr.row][alltr.col] = 2;
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j]+ "");
            }
            System.out.println();
        }
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            for(Position position : positions){
                // System.out.println("\npositions row: "+position.row + "positions col: "+position.col );
                arr[position.row][position.col] = 1;
            }

            for(Position alltr : allt){
                //System.out.println("\ntree row: "+alltr.row + "tree col: "+alltr.col );
                arr[alltr.row][alltr.col] = 2;
            }
            writer.println("OK");
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    writer.print(arr[i][j]);
                }
                writer.println();

            }
            writer.close();
        }catch(IOException e){
            System.out.println(
                    "Error Printing file '"
            );

        }

    }
    private static void printArrdfs(ArrayList<Position> positions, int p, ArrayList<Position> allt) {
        int [][] arr= new int [p][p];
        System.out.println("\npositions length: " + positions.size()+ " p: "+p);

        for(Position position : positions){
            //System.out.println("\npositions row: "+position.row + "positions col: "+position.col );
            arr[position.row][position.col] = 1;
        }

        for(Position alltr : allt){
            //System.out.println("\ntree row: "+alltr.row + "tree col: "+alltr.col );
            arr[alltr.row][alltr.col] = 2;
        }
        for(int i=0; i<p; i++){
            for(int j=0; j<p; j++){
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            for(Position position : positions){
                // System.out.println("\npositions row: "+position.row + "positions col: "+position.col );
                arr[position.row][position.col] = 1;
            }

            for(Position alltr : allt){
                //System.out.println("\ntree row: "+alltr.row + "tree col: "+alltr.col );
                arr[alltr.row][alltr.col] = 2;
            }
            writer.println("OK");
            for(int i=0; i<p; i++){
                for(int j=0; j<p; j++){
                    writer.print(arr[i][j]);
                }
                writer.println();

            }
            writer.close();
        }catch(IOException e){
            System.out.println(
                    "Error Printing file '"
            );

        }
    }



    public static void main(String[] args) {

        int n = 0;
        int t = 0;
        int counter = 0;
        int tempk = 0;
        char[][] tarr;
        String alg = "DFS";
        //currentState[0]=new Position(0,0);



        // The name of the file to open.
        String fileName = "input.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            alg = bufferedReader.readLine();
            String strN = bufferedReader.readLine();
            String strT = bufferedReader.readLine();
            n = Integer.parseInt(strN);
            t = Integer.parseInt(strT);
            tarr = new char[n][n];

            currentState = new Position[t];
            lizards = new Position[t];


            while ((line = bufferedReader.readLine()) != null && counter < n) {
                tarr[counter] = line.toCharArray();
                //System.out.println(tarr[counter]);
                counter++;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tarr[j][i] == '2') {
                        allt.add(new Position(j, i));
                       /* System.out.print(allt.get(allt.size()-1).row);
                        System.out.println(allt.get(allt.size()-1).row);*/

                    } else {
                        if (tempk < t) {
                            //System.out.println("Inserting value (" + j + ", " + i + ")"+ "value of tempk:"+tempk+" Value of t:"+t);
                            //System.out.println(currentState);
                            currentState[tempk] = new Position(j, i);

                            tempk++;
                            //System.out.println(tempk);
                        }
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        LizAndTrees h=new LizAndTrees();
        // Always close files.
        start = System.currentTimeMillis();
        switch (alg) {
            case "DFS":

                    if (h.ZooSolution(n, t, allt)){
                        printArrdfs(tempdfs,n,allt);
                    }
                    else {
                        printFail();
                        System.out.println("FAIL");
                    }

                break;

            case "BFS":

                    if (h.bfsSolution(n, t, allt)) {
                        printArrbfs(correctSolution, n, allt);

                    } else {
                        printFail();
                        System.out.println("FAIL");
                    }
                    break;

            case "SA":
                if (tempk == t && SA(t, n,allt)) {

                    printArrSA(allt,currentState, n);
                } else {
                    printFail();
                    System.out.println("FAIL");
                }
                break;
        }

        long end = System.currentTimeMillis();
        System.out.println((end-start) + "ms");

    }

}
