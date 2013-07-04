package org.devinprogress.SimpleSignEditor;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.block.Sign;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleSignEditor extends JavaPlugin {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	try{
		if (cmd.getName().equalsIgnoreCase("editsign")){
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if (args.length<2){return false;}
				Player player = (Player) sender;
				Block signblock=player.getTargetBlock(null, 2);
				if ((signblock.getTypeId()!=63)&&(signblock.getTypeId()!=68)){
					player.sendMessage("You should look at a sign.");
					return true;
				}
				Sign sign=(Sign)(signblock.getState());
				int line=Integer.parseInt(args[0])-1;
				if ((line>3)||(line<0)){
					player.sendMessage("Line number invalid!");
					return true;
				}
				String linetext=sign.getLine(line);
				if (!(linetext.equals("[Editable]"))){
					player.sendMessage("You should type [Editable] on that line!");
					return true;
				}
				StringBuilder sb = new StringBuilder(args[1]);
				for(int i = 2; i < args.length; i++)
				{
				    sb.append(' ');
				    sb.append(args[ i ]); 
				}
				String total = sb.toString();
				if (total.length()>15){
					player.sendMessage("Line too long!");
					return true;
				}
				sign.setLine(line,total);
				sign.update(true);
				player.sendMessage("Done!");
			}
			return true;
		}
		return false;
	}catch(Throwable ex){
		return false;
	}
	}
}