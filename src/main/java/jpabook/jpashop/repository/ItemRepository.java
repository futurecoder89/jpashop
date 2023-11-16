package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){ //id값이 없다는것은 db에 등록된적이없다는것 그래서 null일때 db에 *신규* itemId값을 영속화 함.
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item); //getId했는데 id값이 나온다면, 그 아이템은 이미 db에 영속화 되었었다는것. merge는 htttp method의 update와 비슷하다
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
