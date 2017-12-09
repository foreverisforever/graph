package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class graph {
    public List<edg> alledg = new ArrayList<edg>();
    public int p;
    public int e;
    public HashMap point_edgs= new HashMap();
    //public HashMap<edg> points 
    //边的set
    //顶点边的hashmap<edg> key是定点的标号
    public graph( int point,int edg )
    {
        edg[] edgarr = new edg[ edg ];
    }
    public void prim()
    {
    	int i = 5; 
    	HashMap res = new HashMap();
    	HashSet edgs = (HashSet)point_edgs.get( i );
    	Iterator it = edgs.iterator();
    	int r;
    	while(it.hasNext())
    	{
    		//m循环每个边
    		edg edg = (edg)it.next();
    		if( edg.v == i )
    		{
    			r = edg.m;
    		}else
    		{
    			r = edg.v;
    		}
    		edg e= (edg)res.get( r );
			if( r<e.l )
			{
				res.put(r, edg);
			}
    	}
    	//point_edgs.put(i, edgs);
        //划分区域  每次进来一个  与外面比较  有更小的则替换掉

    }
    public void kruskal()
    {
    	List res = new ArrayList();
        //每次找最小的边(排序)
        //并查集//只要不构成环就进行下去（判断环根据 不断add边，判断属于哪个组别，如果把组别联通则循环向上升级组别，不连通开通一个组别，，只要新加的定点不在同一个集合就行s）
    	//对alledg进行排序  注意其是list  放到数组里面
        int[] unionfind = new int[p];//1代表划入的范围
        int union_key_mk = 1;
    	List<edg> edg_list = insert_sofr( alledg  );
    	Iterator iterator = edg_list.iterator();
    	while( iterator.hasNext() )
    	{
    		edg edg= (edg)iterator.next();

    		if( unionfind[ edg.m ] ==1 || unionfind[ edg.v ] == 1 )
    		{
    			//将边放入结果集
    			res.add( edg );
    			unionfind[edg.m]= 1;
    			unionfind[edg.v] = 1;
    		}else
    		{
    			if( unionfind[edg.m]== 0 || unionfind[edg.v] == 0 )
    			{
        			unionfind[edg.m] = union_key_mk;
        			unionfind[edg.v] = union_key_mk;
        			union_key_mk++;
    			}
    			if( unionfind[edg.m] >= 1 || unionfind[edg.v] >= 1 )
    			{
    				if( unionfind[edg.m] >unionfind[edg.v] )
    				{

    					//循环所有的unionfind，只要value = unionfind.get( edg.m ) 就把值赋值 unionfind.get( edg.v )
    					for( int i=0;i<unionfind.length;i++ )
    					{
    						if( unionfind[ i ] == unionfind[edg.m] )
    						{
    							unionfind[ i ] = unionfind[edg.v];
    						}
    					}
    					//unionfind.set(edg.m, unionfind.get( edg.v ));
    				}else if( unionfind[edg.m] < unionfind[edg.v] )
    				{
    					for( int i=0;i<unionfind.length;i++ )
    					{
    						if( unionfind[ i ] == unionfind[edg.v] )
    						{
    							unionfind[ i ] = unionfind[edg.m];
    						}
    					}
    				}
    			}    			
    			
    		}
    		
    	}
    	
    }
    public List<edg>  insert_sofr( List<edg> list  )
    {
        for( int i=0;i<list.size();i++ )
        {
            for( int j=i;j>0;j-- )
            {
                if( list.get(j).l>list.get(j-1).l )
                {
                    edg tmep;
                    tmep = list.get(j);
                    list.set(j, list.get(j-1));
                    list.set(j-1,tmep);
                }
            }
        }
        return list;
    }
}

