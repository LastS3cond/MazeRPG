class Dungeon
{
   private Warrior mc;
   public Dungeon(Warrior player)
   {
      mc=player;
      combat(monster((int)(Math.random()*3+1),1));
   }
   private Warrior[] monster(int amt, int dif)
   {
      Warrior[] monsters=new Warrior[amt];
      for(int i=amt-1;i>=0;i--)
      {
         monsters[i]=new Warrior("Monster",(int)(Math.random()*2+1),(int)(Math.random()*7+1),(int)(Math.random()*7+1), dif);
      }
      return monsters;      
   }
   private boolean combat(Warrior[] w)
   {
      System.out.println(mc+"\n");
      Scanner sc=new Scanner(System.in);
      for(int i=w.length-1;i>=0;i--)
      {
         System.out.println("There are "+(i+1)+" monsters left to kill");
         System.out.print(w[i]);
         boolean cmbtFlag=true;
         while(cmbtFlag)
         {
            System.out.println("\nYour current Health is "+mc.getHP());
            System.out.println("\nHow would you like to attack?\n1.By Physicality(Strength and Speed)\n2.By Intelligence\n3.By Magic");
            int ch=sc.nextInt();
            if(ch==2)
            {
               if(mc.getIQ()-w[i].getIQ()>=5)
               {
                  int dam=(int)(Math.random()*mc.getLck());
                  System.out.println("Through outsmarting an enemy, you have damaged them by "+dam);
                  w[i].setHP(w[i].getHP()-dam);
                  if(w[i].getHP()<1)
                  {
                     System.out.println("You have killed the monster!");
                     cmbtFlag=false;
                  }
               }
               else
               {
                  System.out.println("No damage was done to the enemy");
               }
            }
            else if(ch==3)
            {
               if(mc.getMag()-w[i].getMag()>=5)
               {
                  int dam=(int)(Math.random()*mc.getLck());
                  System.out.println("Through magic, you have damaged the enemy by "+dam);
                  w[i].setHP(w[i].getHP()-dam);
                  if(w[i].getHP()<1)
                  {
                     System.out.println("You have killed the monster!");
                     cmbtFlag=false;
                  }
               }
               else
               {
                  System.out.println("No damage was done to the enemy");
               }
            }
            else
            {
               if((mc.getStr()+mc.getSpd())-(w[i].getDef()+w[i].getAgl())>=10)
               {
                  int dam=(int)(Math.random()*mc.getLck());
                  System.out.println("Through physicality, you have damaged the enemy by "+dam);
                  w[i].setHP(w[i].getHP()-dam);
                  if(w[i].getHP()<1)
                  {
                     System.out.println("You have killed the monster!");
                     cmbtFlag=false;
                  }
               }
               else
               {
                  System.out.println("No damage was done to the enemy");
               }
            }
            if(cmbtFlag)
            {
               System.out.println("\nHow would you like to defend(Cannot be the same as how you attacked)?\n1.By Physicality(Defense and Agility)\n2.By Intelligence\n3.By Magic");
               int choice=sc.nextInt();
               while(choice==ch)
               {
                  System.out.println("It cannot be the same as how you attacked, pick a new number");
                  choice=sc.nextInt();
               }                  
               if(choice==2)
               {
                  if(w[i].getIQ()-mc.getIQ()>=1)
                  {
                     int dam=(int)(Math.random()*w[i].getLck());
                     System.out.println("Through outsmarting you, they have damaged you by "+dam);
                     mc.setHP(mc.getHP()-dam);
                    if(mc.getHP()<1)
                    {
                       System.out.println("The monster has killed you!");
                       cmbtFlag=false;
                    }
                  }
                  else
                  {
                     System.out.println("No damage was done to the you");
                  }
               }
               else if(choice==3)
               {
                  if(w[i].getMag()-mc.getMag()>=1)
                  {
                     int dam=(int)(Math.random()*w[i].getLck());
                     System.out.println("Through magic, they have damaged you by "+dam);
                     mc.setHP(mc.getHP()-dam);
                     if(mc.getHP()<1)
                    {
                       System.out.println("The monster has killed you!");
                       cmbtFlag=false;
                    }
                  }
                  else
                  {
                     System.out.println("No damage was done to the you");
                  }
               }
               else
               {
                  if((w[i].getStr()+w[i].getSpd())-(mc.getDef()+mc.getAgl())>=1)
                  {
                     int dam=(int)(Math.random()*w[i].getLck());
                     System.out.println("Through physicality, the enemy has damaged you by "+dam);
                     mc.setHP(mc.getHP()-dam);
                     if(mc.getHP()<1)
                    {
                       System.out.println("The monster has killed you!");
                       cmbtFlag=false;
                    }
                  }
                  else
                  {
                     System.out.println("No damage was done to the you");
                  }
               }
            }
         }
      if(mc.getHP()<0)
         return true; 
      }   
      if(mc.getHP()<0)
         return true;
      return false;  
   }
}
class Warrior
{
   private String name;
   private String clique;
   private String race;
   private String sex;
   private int str; //strength
   private int def; //defense
   private int spd; //speed
   private int agl; //agility
   private int iq; //intelligence
   private int mag; //magic
   private int lck; //luck
   private int hp;
   public Warrior(String n, String c, String r, String s, int strength, int defense, int speed, int agility, int intelligence, int magic, int luck, int hp)
   {
      name=n;
      clique=c;
      race=r;
      sex=s;
      str=strength;
      def=defense;
      spd=speed;
      agl=agility;
      iq=intelligence;
      mag=magic;
      lck=luck;
      this.hp=hp;
   }
   public Warrior()
   {
      Scanner in=new Scanner(System.in);
      System.out.println("Welcome to character creation!\nWhat would you like your character name to be?");
      name=in.next();
      
      System.out.println("What would you like your character gender to be?\n1. Male(High Str, High Def, High Spd)\n2. Female(High Agl, High IQ, High Mag)");
      int gender=in.nextInt();
      while(!(gender==1||gender==2))
      {
         System.out.println("You failed to enter a valid number /:, please try again.");
         gender=in.nextInt();
      }
      generateGender(gender);
      
      System.out.println("What would you like your character race to be?\n1. Human(Middle)\n2. Elf(High Agl, High Mag, Low Str, Low Def)\n3. Dwarf(High Def, High IQ, Low Agl, Low Mag)\n4. Orc(High Str, High Spd, Low IQ, Low Mag)\n5. Goblin(High Spd, High Agl, Low Str, Low Def)\n6. Druid(High IQ, High Mag, Low Spd, Low Agl)\n7. Ogre(High Str, High Def, Low Spd, Low IQ)");
      int r=in.nextInt();
      while(!(r>0&&r<8))
      {
         System.out.println("You failed to enter a valid number /:, please try again.");
         r=in.nextInt();
      }
      generateRace(r);
      
      System.out.println("What would you like your character class to be?\n1. Mage(High Mag, Low Agl)\n2. Warrior(Middle)\n3. Paladin(High Def, Low Spd)\n4. Brute(High Str, Low IQ)\n5. Mad Scientist(High IQ, Low Mag)\n6. Ranger(High Agl, Low Def)\n7. Assassin(High Spd, Low Str)");
      int c=in.nextInt();
      while(!(c>0&&c<8))
      {
         System.out.println("You failed to enter a valid number /:, please try again.");
         c=in.nextInt();
      }
      generateClass(c);
      
      str=1;
      def=1;
      spd=1;
      agl=1;
      iq=1;
      mag=1;
      lck=(int)(Math.random()*50)+50;
      hp=100;
      generateStats(3);
   }
   public Warrior(String n, int g, int r, int c, int d)
   {
      name=n;
      generateGender(g);
      generateRace(r);
      generateClass(c);
      
      str=1;
      def=1;
      spd=1;
      agl=1;
      iq=1;
      mag=1;
      lck=(int)(Math.random()*50)+50;
      hp=100;
      generateStats(d);
   }
   private void generateClass(int i)
   {
      if(i==1)
         clique="Mage";
      if(i==2)
         clique="Warrior";
      if(i==3)
         clique="Paladin";
      if(i==4)
         clique="Brute";
      if(i==5)
         clique="Mad Scientist";
      if(i==6)
         clique="Ranger";
      if(i==7)
         clique="Assassin";
   }   
   private void generateGender(int i)
   {
      if(i==1)
         sex="Male";
      if(i==2)
         sex="Female";
   }
   private void generateRace(int i)
   {
      if(i==1)
         race="Human";
      if(i==2)
         race="Elf";
      if(i==3)
         race="Dwarf";
      if(i==4)
         race="Orc";
      if(i==5)
         race="Goblin";
      if(i==6)
         race="Druid";
      if(i==7)
         race="Ogre";
   }
   private void generateStats(int d) //Generates random stats based on the clique chosen
   {
      if(clique.equals("Warrior"))
      {
         str+=statGen(d,2);
         def+=statGen(d,2);
         spd+=statGen(d,2);
         agl+=statGen(d,2);
         iq+=statGen(d,2);
         mag+=statGen(d,2);
         
      }
      if(clique.equals("Mage"))
      {
         str+=statGen(d,2);
         def+=statGen(d,2);
         spd+=statGen(d,2);
         agl+=statGen(d,1);
         iq+=statGen(d,2);
         mag+=statGen(d,3);
         
      }
      if(clique.equals("Paladin"))
      {
         str+=statGen(d,2);
         def+=statGen(d,3);
         spd+=statGen(d,1);
         agl+=statGen(d,2);
         iq+=statGen(d,2);
         mag+=statGen(d,2);
         
      }
      if(clique.equals("Brute"))
      {
         str+=statGen(d,3);
         def+=statGen(d,2);
         spd+=statGen(d,2);
         agl+=statGen(d,2);
         iq+=statGen(d,1);
         mag+=statGen(d,2);
         
      }
      if(clique.equals("Mad Scientist"))
      {
         str+=statGen(d,2);
         def+=statGen(d,2);
         spd+=statGen(d,2);
         agl+=statGen(d,2);
         iq+=statGen(d,3);
         mag+=statGen(d,1);
         
      }
      if(clique.equals("Ranger"))
      {
         str+=statGen(d,2);
         def+=statGen(d,1);
         spd+=statGen(d,2);
         agl+=statGen(d,3);
         iq+=statGen(d,2);
         mag+=statGen(d,2);
         
      }
      if(clique.equals("Assassin"))
      {
         str+=statGen(d,1);
         def+=statGen(d,2);
         spd+=statGen(d,3);
         agl+=statGen(d,2);
         iq+=statGen(d,2);
         mag+=statGen(d,2);
         
      }
      
      if(race.equals("Human"))
      {
         str+=statGen(d,2);
         def+=statGen(d,2);
         spd+=statGen(d,2);
         agl+=statGen(d,2);
         iq+=statGen(d,2);
         mag+=statGen(d,2);
         
      }
      if(race.equals("Elf"))
      {
         str+=statGen(d,1);
         def+=statGen(d,1);
         spd+=statGen(d,2);
         agl+=statGen(d,3);
         iq+=statGen(d,2);
         mag+=statGen(d,3);
         
      }
      if(race.equals("Dwarf"))
      {
         str+=statGen(d,2);
         def+=statGen(d,3);
         spd+=statGen(d,2);
         agl+=statGen(d,1);
         iq+=statGen(d,3);
         mag+=statGen(d,1);
         
      }
      if(race.equals("Goblin"))
      {
         str+=statGen(d,1);
         def+=statGen(d,1);
         spd+=statGen(d,3);
         agl+=statGen(d,3);
         iq+=statGen(d,2);
         mag+=statGen(d,2);
         
      }
      if(race.equals("Ogre"))
      {
         str+=statGen(d,3);
         def+=statGen(d,3);
         spd+=statGen(d,1);
         agl+=statGen(d,2);
         iq+=statGen(d,1);
         mag+=statGen(d,2);
         
      }
      if(race.equals("Druid"))
      {
         str+=statGen(d,2);
         def+=statGen(d,2);
         spd+=statGen(d,1);
         agl+=statGen(d,1);
         iq+=statGen(d,3);
         mag+=statGen(d,3);
         
      }
      if(race.equals("Orc"))
      {
         str+=statGen(d,3);
         def+=statGen(d,2);
         spd+=statGen(d,3);
         agl+=statGen(d,2);
         iq+=statGen(d,1);
         mag+=statGen(d,1);
         
      }
      
      if(sex.equals("Male"))
      {
         str+=statGen(d,3);
         def+=statGen(d,3);
         spd+=statGen(d,3);
         agl+=statGen(d,1);
         iq+=statGen(d,1);
         mag+=statGen(d,1);
         
      }
      if(sex.equals("Female"))
      {
         str+=statGen(d,1);
         def+=statGen(d,1);
         spd+=statGen(d,1);
         agl+=statGen(d,3);
         iq+=statGen(d,3);
         mag+=statGen(d,3);
         
      }
         
   }
   public int statGen(int d,int c)
   {
      if(c==1)
         return (int)(Math.random()*(12);
      else if(c==3)
         return (int)(Math.random()*(11)+7*d+2);
      else
         return (int)(Math.random()*(11)+4*d);
   }
   
   public int getStr()
   {
      return str;
   }
   public void setStr(int str)
   {
      this.str=str;
   }
   
   public int getDef()
   {
      return def;
   }
   public void setDef(int def)
   {
      this.def=def;
   }
   
   public int getSpd()
   {
      return spd;
   }
   public void setSpd(int spd)
   {
      this.spd=spd;
   }
   
   public int getAgl()
   {
      return agl;
   }
   public void setAgl(int agl)
   {
      this.agl=agl;
   }
   
   public int getIQ()
   {
      return iq;
   }
   public void setIQ(int iq)
   {
      this.iq=iq;
   }
   
   public int getMag()
   {
      return mag;
   }
   public void setMag(int mag)
   {
      this.mag=mag;
   }
   
   public int getLck()
   {
      return lck;
   }
   public void setLck(int lck)
   {
      this.lck=lck;
   }
   
   
   public int getHP()
   {
      return hp;
   }
   public void setHP(int hp)
   {
      this.hp=hp;
   }
   
   public String getName() //returns name
   {
      return name;
   }
   public String getSex() //returns clique
   {
      return sex;
   }   
   public String getRace() //returns clique
   {
      return race;
   }
   public String getClique() //returns clique
   {
      return clique;
   }
   public String toString() //Allows for easy printing of Object Variables
   {
      return "Name: "+name+"\tClique: "+clique+"\tRace: "+race+"   Gender: "+sex+"\nStats:\nStrength: "+str+"\nDefense: "+def+"\nSpeed: "+spd+"\nAgility: "+agl+"\nIntelligence: "+iq+"\nMagic: "+mag+"\nLuck: "+lck+"\nHealth: "+hp;
   }
}
