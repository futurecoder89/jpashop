package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity ){  //findItem을 반환해준다 밑 코드는 merge()를 풀어쓴 메소드이다
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        /*return findItem;*/
        /*itemRepository.save(findItem); 을 할필요가없다 */
        //준영속 상태 entity이기때문에 스프링의 transaction에 의해서
        /*commit이되면 flush()가 생성된다 그러면 자동으로 update를 한다*/
        //하지만 setter로 하는건 좋지않은 코드이다 findItem.change(name price stockQuantity)같은 메소드를 만들어서 하자.
    }
}
