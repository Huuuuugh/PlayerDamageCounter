package PlayerDamageCounter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class a extends JavaPlugin implements Listener {
    public static List<String> List = new ArrayList<String>();

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this,this);
        getLogger().info("PlayerDamageCounter Loaded -Huuuuugh");
        List.add("--全服伤害记录--##0");
    }
    @Override
    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        if(c.getName().equals("damagecounter")){
            //System.out.println(List);
            String b=null;
            for(String a:List){
                b=a;
                s.sendMessage(b);
            }
        }
        if(c.getName().equals("damagereset")&&s.isOp()){
            //System.out.println(List);
            List.clear();
            List.add("--全服伤害记录--##0");
        }
        return true;
    }
    @EventHandler
    public void onDamaged(EntityDamageByEntityEvent e){
        //System.out.println(e.getDamager().getType());
        String s;
        if(e.getDamager().getType().toString().equals("PLAYER")){
            //System.out.println(e.getDamager().getUniqueId());
            //System.out.println("##########"+getServer().getPlayer(e.getDamager().getUniqueId()).getDisplayName());
            s = (getServer().getPlayer(e.getDamager().getUniqueId()).getName() +"##"+ (int)e.getFinalDamage());
            System.out.println(s);
            //if(List!=null){
                for(String a:List){//查询该人
                    String qwq[] = a.split("##");//分割变量 0是名字 1是数值
                    if(qwq[0].equals(getServer().getPlayer(e.getDamager().getUniqueId()).getName())){//具体搜索
                        String temp=List.get(List.indexOf(a));//搜索到旧的序列
                        String[] tempArray = temp.split("##");//进行计算
                        int damage = Integer.parseInt(tempArray[1]) + (int)e.getFinalDamage();
                        //重新储存
                        List.remove(a);
                        List.add(tempArray[0]+"##"+damage);
                    }else if(List.indexOf(a)==List.size()-1){
                        List.add(s);//**在末端加上新数据
                    }


                }
            //}else{
            //    List.add(s);
            //}

            //List.add(s);
        }


        //System.out.println(e.getFinalDamage());
    }
/*    @EventHandler
    public void doDamage(EntityDamageEvent e){
        if(temp!=null){
            temp2 = temp + "##" + e.getFinalDamage();
            List.add(temp2);
            for(String a:List){
                String[] b=a.split("##");
                String[] c=List.get(0).split("##");
                if(b[0].equals(c[0])){
                    j=j+Integer.parseInt(c[1]);
                }
                List2.add(c[0]+"##"+j);
                j=0;
            }
            List.remove(List.get(0));
            temp=null;
        }

    }
    @EventHandler
    public void PlayerDamage(PlayerInteractEntityEvent e){
        //if(e.getEventName().equals("ENTITY_ATTACK")){//这里有可能出问题
            temp = e.getPlayer().getDisplayName();
        //}

    }*/
}
