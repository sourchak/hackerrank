import java.util.Scanner;

public class Solution
{
    private int a[][];
    Solution(int i,int j)
    {
        a=new int[i][j];
    }
    //if you run in from main, you'll have to add the array elements from your console
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int m=s.nextInt();
        int n=s.nextInt();
        int k=s.nextInt();
        int temp=0,flag=0;
        Solution o=new Solution(m,n);
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
            {
                o.a[i][j]=s.nextInt();
                if(j==0 && i==0)
                    temp=o.a[0][0];
                else if(flag==0 && temp!=o.a[i][j])//this check if all elements in the array are equal
                    flag=1;
            }
        if(flag==0)
            o.print(o.a);
        else
        {
            int[][] b=o.rotator(o.a,k);
            o.print(b);
        }
    }
    //call this and pass in the array and the number of places by which you want to rotate each of its layers
    //constraint: min(numberOfRows,numberOfColumns)%2=0
    int[][] rotator(int a[][], int k) 
    {
        if(a!=null && a[0].length!=0)
        {
            int m=a.length;
            int n=a[0].length;//this will fail if a is empty
            Solution o=new Solution(m,n);
            o.a=a;
            int b[][]=new int[m][n];
            int new_index[]=new int[2];
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)
                {
                    int I=i,J=j;
                    int l=(m-i-1)<i?(m-i-1):i;
                    int l_1=(n-1-j)<j?(n-j-1):j;
                    l=l<l_1?l:l_1;
                    int k_actual=k%(2*(m+n-4*l-2));
                    new_index=new int [] {i,j,k_actual,l};
                    if(i==l && k_actual>0) //element is in the top row
                    {
                        if(k_actual>0)
                            new_index = o.goLeft(I,J,k_actual,l);//going left
                        if(new_index[2]>0)
                            new_index= o.goDown(new_index[0],new_index[1],new_index[2],l);//going down
                        if(new_index[2]>0)
                            new_index=o.goRight(new_index[0],new_index[1],new_index[2],l);//going right
                        if(new_index[2]>0)
                            new_index=o.goUp(new_index[0],new_index[1],new_index[2],l);//going up
                        if(new_index[2]>0)
                            new_index=o.goLeft(new_index[0],new_index[1],new_index[2],l);//going left
                    }

                    else if(j==l && k_actual>0) //element is in the left column
                    {
                       if(new_index[2]>0)
                            new_index= o.goDown(new_index[0],new_index[1],new_index[2],l);//going down
                        if(new_index[2]>0)
                            new_index=o.goRight(new_index[0],new_index[1],new_index[2],l);//going right
                        if(new_index[2]>0)
                            new_index=o.goUp(new_index[0],new_index[1],new_index[2],l);//going up
                        if(new_index[2]>0)
                            new_index=o.goLeft(new_index[0],new_index[1],new_index[2],l);//going left
                        if(new_index[2]>0)
                            new_index= o.goDown(new_index[0],new_index[1],new_index[2],l);//going down                        
                    }

                    else if(i==m-1-l && k_actual>0)//element is in the bottom row
                    {
                        if(new_index[2]>0)
                            new_index=o.goRight(new_index[0],new_index[1],new_index[2],l);//going right
                        if(new_index[2]>0)
                            new_index=o.goUp(new_index[0],new_index[1],new_index[2],l);//going up
                        if(new_index[2]>0)
                            new_index=o.goLeft(new_index[0],new_index[1],new_index[2],l);//going left
                        if(new_index[2]>0)
                            new_index= o.goDown(new_index[0],new_index[1],new_index[2],l);//going down
                        if(new_index[2]>0)
                            new_index=o.goRight(new_index[0],new_index[1],new_index[2],l);//going right
                    }
                    else if(j==n-1-l && k_actual>0)//element is in the right column
                    {
                        if(new_index[2]>0)
                            new_index=o.goUp(new_index[0],new_index[1],new_index[2],l);//going up
                        if(new_index[2]>0)
                            new_index=o.goLeft(new_index[0],new_index[1],new_index[2],l);//going left
                        if(new_index[2]>0)
                            new_index= o.goDown(new_index[0],new_index[1],new_index[2],l);//going down
                        if(new_index[2]>0)
                            new_index=o.goRight(new_index[0],new_index[1],new_index[2],l);//going right
                        if(new_index[2]>0)
                            new_index=o.goUp(new_index[0],new_index[1],new_index[2],l);//going up
                    }
                    I=new_index[0];
                    J=new_index[1];
                    b[I][J]=o.a[i][j];
                }
            }
            return b;
        }
        else
        {
            System.out.println("Empty Array");
            return null;
        }
    }
    int[] goLeft(int i,int j, int k, int l)
    {
        if(k>(j-l))
            return new int[] {i,l,k-(j-l)};
        else
            return new int[] {i,j-k,0};
    }
    int[] goDown(int i, int j, int k, int l)
    {
        int m=a.length;
        if(k>(m-1-l-i))
            return new int[] {(m-1-l),j,k-(m-l-i-1)};
        else
            return new int [] {i+k,j,0};
    }
    int[] goRight(int i, int j, int k, int l)
    {
        int n=a[0].length;
        if(k>(n-l-1-j))
            return new int [] {i,(n-l-1),k-(n-l-1-j)};
        else
            return new int[] {i,j+k,0};
    }
    int[] goUp(int i, int j, int k, int l)
    {
        if(k>(i-l))
            return new int[] {l,j,k-(i-l)};
        else
            return new int[] {i-k,j,0};
    }
    void print(int [][] a)
    {
        for(int[] x:a)
        {
            for(int y:x)
                System.out.print(y+" ");
            System.out.println();
        }
    }
}
