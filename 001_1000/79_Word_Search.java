class Solution {
    public boolean exist(char[][] board, String word) {
        int track[][] = new int [board.length][board[0].length];

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==word.charAt(0)&& find(i,j,0,word,board,track)){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean find(int r, int c,int idx, String word, char[][] board, int track[][]){

        if(idx==word.length()){
            return true;
        }

        if(r>=board.length || c>= board[0].length || r<0 || c<0 || board[r][c]!=word.charAt(idx) || track[r][c]==1){
            return false;
        }

        track[r][c]=1;

        boolean d = find(r+1,c,idx+1,word,board,track);
        boolean u = find(r-1,c,idx+1,word,board,track);
        boolean ri = find(r,c+1,idx+1,word,board,track);
        boolean l = find(r,c-1,idx+1,word,board,track);

        track[r][c] = 0;
        return d || u || ri ||l;
    }
}