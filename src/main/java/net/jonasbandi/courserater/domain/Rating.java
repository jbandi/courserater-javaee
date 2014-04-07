package net.jonasbandi.courserater.domain;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Rating implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;

   @Column(length = 50)
   private String participantName;

   @Column
   private int score;

   @Temporal(TemporalType.DATE)
   private Date courseDate;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Rating) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getParticipantName()
   {
      return this.participantName;
   }

   public void setParticipantName(final String participantName)
   {
      this.participantName = participantName;
   }

   public int getScore()
   {
      return this.score;
   }

   public void setScore(final int score)
   {
      this.score = score;
   }

   public Date getCourseDate()
   {
      return this.courseDate;
   }

   public void setCourseDate(final Date courseDate)
   {
      this.courseDate = courseDate;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (participantName != null && !participantName.trim().isEmpty())
         result += "participantName: " + participantName;
      result += ", score: " + score;
      return result;
   }
}