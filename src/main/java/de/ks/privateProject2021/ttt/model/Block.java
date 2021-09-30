package de.ks.privateProject2021.ttt.model;
import java.beans.PropertyChangeSupport;

public class Block
{
   public static final String PROPERTY_X_POS = "xPos";
   public static final String PROPERTY_Y_POS = "yPos";
   public static final String PROPERTY_POSITION_VALUE = "positionValue";
   public static final String PROPERTY_MARKED_WITH = "markedWith";
   public static final String PROPERTY_PLAYER = "player";
   public static final String PROPERTY_GAME = "game";
   private int xPos;
   private int yPos;
   private int positionValue;
   private int markedWith;
   private Player player;
   private Game game;
   protected PropertyChangeSupport listeners;

   public int getXPos()
   {
      return this.xPos;
   }

   public Block setXPos(int value)
   {
      if (value == this.xPos)
      {
         return this;
      }

      final int oldValue = this.xPos;
      this.xPos = value;
      this.firePropertyChange(PROPERTY_X_POS, oldValue, value);
      return this;
   }

   public int getYPos()
   {
      return this.yPos;
   }

   public Block setYPos(int value)
   {
      if (value == this.yPos)
      {
         return this;
      }

      final int oldValue = this.yPos;
      this.yPos = value;
      this.firePropertyChange(PROPERTY_Y_POS, oldValue, value);
      return this;
   }

   public int getPositionValue()
   {
      return this.positionValue;
   }

   public Block setPositionValue(int value)
   {
      if (value == this.positionValue)
      {
         return this;
      }

      final int oldValue = this.positionValue;
      this.positionValue = value;
      this.firePropertyChange(PROPERTY_POSITION_VALUE, oldValue, value);
      return this;
   }

   public int getMarkedWith()
   {
      return this.markedWith;
   }

   public Block setMarkedWith(int value)
   {
      if (value == this.markedWith)
      {
         return this;
      }

      final int oldValue = this.markedWith;
      this.markedWith = value;
      this.firePropertyChange(PROPERTY_MARKED_WITH, oldValue, value);
      return this;
   }

   public Player getPlayer()
   {
      return this.player;
   }

   public Block setPlayer(Player value)
   {
      if (this.player == value)
      {
         return this;
      }

      final Player oldValue = this.player;
      if (this.player != null)
      {
         this.player = null;
         oldValue.withoutOwnedBlocks(this);
      }
      this.player = value;
      if (value != null)
      {
         value.withOwnedBlocks(this);
      }
      this.firePropertyChange(PROPERTY_PLAYER, oldValue, value);
      return this;
   }

   public Game getGame()
   {
      return this.game;
   }

   public Block setGame(Game value)
   {
      if (this.game == value)
      {
         return this;
      }

      final Game oldValue = this.game;
      if (this.game != null)
      {
         this.game = null;
         oldValue.withoutBlockField(this);
      }
      this.game = value;
      if (value != null)
      {
         value.withBlockField(this);
      }
      this.firePropertyChange(PROPERTY_GAME, oldValue, value);
      return this;
   }

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (this.listeners != null)
      {
         this.listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public PropertyChangeSupport listeners()
   {
      if (this.listeners == null)
      {
         this.listeners = new PropertyChangeSupport(this);
      }
      return this.listeners;
   }

   public void removeYou()
   {
      this.setPlayer(null);
      this.setGame(null);
   }
}
