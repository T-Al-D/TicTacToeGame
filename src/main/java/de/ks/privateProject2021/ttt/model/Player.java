package de.ks.privateProject2021.ttt.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.Collection;
import java.beans.PropertyChangeSupport;

public class Player
{
   public static final String PROPERTY_PLAYER_NAME = "playerName";
   public static final String PROPERTY_PLAYER_ID = "playerID";
   public static final String PROPERTY_VICTORY = "victory";
   public static final String PROPERTY_OWNED_BLOCKS = "ownedBlocks";
   public static final String PROPERTY_GAME = "game";
   private String playerName;
   private int playerID;
   private boolean victory;
   private List<Block> ownedBlocks;
   private Game game;
   protected PropertyChangeSupport listeners;

   public String getPlayerName()
   {
      return this.playerName;
   }

   public Player setPlayerName(String value)
   {
      if (Objects.equals(value, this.playerName))
      {
         return this;
      }

      final String oldValue = this.playerName;
      this.playerName = value;
      this.firePropertyChange(PROPERTY_PLAYER_NAME, oldValue, value);
      return this;
   }

   public int getPlayerID()
   {
      return this.playerID;
   }

   public Player setPlayerID(int value)
   {
      if (value == this.playerID)
      {
         return this;
      }

      final int oldValue = this.playerID;
      this.playerID = value;
      this.firePropertyChange(PROPERTY_PLAYER_ID, oldValue, value);
      return this;
   }

   public boolean isVictory()
   {
      return this.victory;
   }

   public Player setVictory(boolean value)
   {
      if (value == this.victory)
      {
         return this;
      }

      final boolean oldValue = this.victory;
      this.victory = value;
      this.firePropertyChange(PROPERTY_VICTORY, oldValue, value);
      return this;
   }

   public List<Block> getOwnedBlocks()
   {
      return this.ownedBlocks != null ? Collections.unmodifiableList(this.ownedBlocks) : Collections.emptyList();
   }

   public Player withOwnedBlocks(Block value)
   {
      if (this.ownedBlocks == null)
      {
         this.ownedBlocks = new ArrayList<>();
      }
      if (!this.ownedBlocks.contains(value))
      {
         this.ownedBlocks.add(value);
         value.setPlayer(this);
         this.firePropertyChange(PROPERTY_OWNED_BLOCKS, null, value);
      }
      return this;
   }

   public Player withOwnedBlocks(Block... value)
   {
      for (final Block item : value)
      {
         this.withOwnedBlocks(item);
      }
      return this;
   }

   public Player withOwnedBlocks(Collection<? extends Block> value)
   {
      for (final Block item : value)
      {
         this.withOwnedBlocks(item);
      }
      return this;
   }

   public Player withoutOwnedBlocks(Block value)
   {
      if (this.ownedBlocks != null && this.ownedBlocks.remove(value))
      {
         value.setPlayer(null);
         this.firePropertyChange(PROPERTY_OWNED_BLOCKS, value, null);
      }
      return this;
   }

   public Player withoutOwnedBlocks(Block... value)
   {
      for (final Block item : value)
      {
         this.withoutOwnedBlocks(item);
      }
      return this;
   }

   public Player withoutOwnedBlocks(Collection<? extends Block> value)
   {
      for (final Block item : value)
      {
         this.withoutOwnedBlocks(item);
      }
      return this;
   }

   public Game getGame()
   {
      return this.game;
   }

   public Player setGame(Game value)
   {
      if (this.game == value)
      {
         return this;
      }

      final Game oldValue = this.game;
      if (this.game != null)
      {
         this.game = null;
         oldValue.withoutPlayers(this);
      }
      this.game = value;
      if (value != null)
      {
         value.withPlayers(this);
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

   @Override
   public String toString()
   {
      final StringBuilder result = new StringBuilder();
      result.append(' ').append(this.getPlayerName());
      return result.substring(1);
   }

   public void removeYou()
   {
      this.withoutOwnedBlocks(new ArrayList<>(this.getOwnedBlocks()));
      this.setGame(null);
   }
}
