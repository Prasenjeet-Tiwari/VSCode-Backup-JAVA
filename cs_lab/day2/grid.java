public class grid{

    public static void func(int i, int j, int m, int n, StringBuilder sb){
        if(i>=n && j>=m){
            System.out.println(sb.toString());
            return;
        }
        if( i>=n ){
            func(i, j+1, m, n, sb.replace(sb.length()-1, sb.length(), "R"));
        }
        if( j>=m){
            func(i, j+1, m, n, sb.replace(sb.length()-1, sb.length(), "L"));
        }
        func(i+1, j, m, n, sb.append('L'));
        func(i, j+1, m, n, sb.append('R'));
    }
    public static void main(String[] args) {
        int n=5, m=5, i=0,j=0;

        StringBuilder sb= new StringBuilder();

        func(i,j,m,n,sb);

    }
}