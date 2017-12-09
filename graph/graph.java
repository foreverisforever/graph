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
    //�ߵ�set
    //����ߵ�hashmap<edg> key�Ƕ���ı��
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
    		//mѭ��ÿ����
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
        //��������  ÿ�ν���һ��  ������Ƚ�  �и�С�����滻��

    }
    public void kruskal()
    {
    	List res = new ArrayList();
        //ÿ������С�ı�(����)
        //���鼯//ֻҪ�����ɻ��ͽ�����ȥ���жϻ����� ����add�ߣ��ж������ĸ��������������ͨ��ѭ������������𣬲���ͨ��ͨһ����𣬣�ֻҪ�¼ӵĶ��㲻��ͬһ�����Ͼ���s��
    	//��alledg��������  ע������list  �ŵ���������
        int[] unionfind = new int[p];//1������ķ�Χ
        int union_key_mk = 1;
    	List<edg> edg_list = insert_sofr( alledg  );
    	Iterator iterator = edg_list.iterator();
    	while( iterator.hasNext() )
    	{
    		edg edg= (edg)iterator.next();

    		if( unionfind[ edg.m ] ==1 || unionfind[ edg.v ] == 1 )
    		{
    			//���߷�������
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

    					//ѭ�����е�unionfind��ֻҪvalue = unionfind.get( edg.m ) �Ͱ�ֵ��ֵ unionfind.get( edg.v )
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

