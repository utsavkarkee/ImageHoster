package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment createComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
        }

        return comment;
    }

    public Comment findComment(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = em.createQuery("Select c from Comment c where c.id =:commentId", Comment.class)
                    .setParameter("commentId", id);
            return typedQuery.getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }
}
