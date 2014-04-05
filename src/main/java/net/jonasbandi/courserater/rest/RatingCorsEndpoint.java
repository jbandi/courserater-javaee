package net.jonasbandi.courserater.rest;

import net.jonasbandi.courserater.domain.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 * 
 */
@Stateless
@Path("/ratings-cors")
public class RatingCorsEndpoint
{
   @PersistenceContext(unitName = "forge-default")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Rating entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(RatingCorsEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Rating entity = em.find(Rating.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Rating> findByIdQuery = em.createQuery("SELECT DISTINCT r FROM Rating r WHERE r.id = :entityId ORDER BY r.id", Rating.class);
      findByIdQuery.setParameter("entityId", id);
      Rating entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Rating> listAll()
   {
      final List<Rating> results = em.createQuery("SELECT DISTINCT r FROM Rating r ORDER BY r.id", Rating.class).getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(Rating entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}