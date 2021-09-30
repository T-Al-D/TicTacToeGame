package de.ks.privateProject2021.ttt.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.Collection;
import java.beans.PropertyChangeSupport;

public class Game
{
   public static final String PROPERTY_GAME_NAME = "gameName";
   public static final String PROPERTY_FIELD_LENGTH = "fieldLength";
   public static final String PROPERTY_BLOCK_FIELD = "blockField";
   public static final String PROPERTY_PLAYERS = "players";
   private String gameName;
   private int fieldLength;
   private List<Block> blockField;
   private List<Player> players;
   protected PropertyChangeSupport listeners;

   public String getGameName()
   {
      return this.gameName;
   }

   public Game setGameName(String value)
   {
      if (Objects.equals(value, this.gameName))
      {
         return this;
      }

      final String oldValue = this.gameName;
      this.gameName = value;
      this.firePropertyChange(PROPERTY_GAME_NAME, oldValue, value);
      return this;
   }

   public int getFieldLength()
   {
      return this.fieldLength;
   }

   public Game setFieldLength(int value)
   {
      if (value == this.fieldLength)
      {
         return this;
      }

      final int oldValue = this.fieldLength;
      this.fieldLength = value;
      this.firePropertyChange(PROPERTY_FIELD_LENGTH, oldValue, value);
      return this;
   }

   public List<Block> getBlockField()
   {
      return this.blockField != null ? Collections.unmodifiableList(this.blockField) : Collections.emptyList();
   }

   public Game withBlockField(Block value)
   {
      if (this.blockField == null)
      {
         this.blockField = new ArrayList<>();
      }
      if (!this.blockField.contains(value))
      {
         this.blockField.add(value);
         value.setGame(this);
         this.firePropertyChange(PROPERTY_BLOCK_FIELD, null, value);
      }
      return this;
   }

   public Game withBlockField(Block... value)
   {
      for (final Block item : value)
      {
         this.withBlockField(item);
      }
      return this;
   }

   public Game withBlockField(Collection<? extends Block> value)
   {
      for (final Block item : value)
      {
         this.withBlockField(item);
      }
      return this;
   }

   public Game withoutBlockField(Block value)
   {
      if (this.blockField != null && this.blockField.remove(value))
      {
         value.setGame(null);
         this.firePropertyChange(PROPERTY_BLOCK_FIELD, value, null);
      }
      return this;
   }

   public Game withoutBlockField(Block... value)
   {
      for (final Block item : value)
      {
         this.withoutBlockField(item);
      }
      return this;
   }

   public Game withoutBlockField(Collection<? extends Block> value)
   {
      for (final Block item : value)
      {
         this.withoutBlockField(item);
      }
      return this;
   }

   public List<Player> getPlayers()
   {
      return this.players != null ? Collections.unmodifiableList(this.players) : Collections.emptyList();
   }

   public Game withPlayers(Player value)
   {
      if (this.players == null)
      {
         this.players = new ArrayList<>();
      }
      if (!this.players.contains(value))
      {
         this.players.add(value);
         value.setGame(this);
         this.firePropertyChange(PROPERTY_PLAYERS, null, value);
      }
      return this;
   }

   public Game withPlayers(Player... value)
   {
      for (final Player item : value)
      {
         this.withPlayers(item);
      }
      return this;
   }

   public Game withPlayers(Collection<? extends Player> value)
   {
      for (final Player item : value)
      {
         this.withPlayers(item);
      }
      return this;
   }

   public Game withoutPlayers(Player value)
   {
      if (this.players != null && this.players.remove(value))
      {
         value.setGame(null);
         this.firePropertyChange(PROPERTY_PLAYERS, value, null);
      }
      return this;
   }

   public Game withoutPlayers(Player... value)
   {
      for (final Player item : value)
      {
         this.withoutPlayers(item);
      }
      return this;
   }

   public Game withoutPlayers(Collection<? extends Player> value)
   {
      for (final Player item : value)
      {
         this.withoutPlayers(item);
      }
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
      result.append(' ').append(this.getGameName());
      return result.substring(1);
   }

   public void removeYou()
   {
      this.withoutBlockField(new ArrayList<>(this.getBlockField()));
      this.withoutPlayers(new ArrayList<>(this.getPlayers()));
   }
}
