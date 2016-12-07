import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TODO
 * - Find the transaction with the smallest value.
 *
 */

public class TransactionsShould {

    private Trader mario;
    private Trader alan;
    private Trader brian;
    private Trader raoul;
    private List<Transaction> transactions;

    @Before
    public void before(){
        mario = new Trader("Mario","Milan");
        alan = new Trader("Alan","Cambridge");
        brian = new Trader("Brian","Cambridge");
        raoul = new Trader("Raoul", "Cambridge");

        transactions = Arrays.asList( new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void collect_all_transactions_made_in_2011_sorted_by_value (){

        List<Transaction> resultExpected = Arrays.asList(new Transaction(brian, 2011, 300),
                                                             new Transaction(raoul, 2011, 400));

        List<Transaction> transactionsSorted = transactions.stream()
                            .filter(transaction -> transaction.getYear() == 2011)
                            .sorted((t1, t2) -> t1.getValue() - t2.getValue())
                            .collect(Collectors.toList());

        assertEquals(resultExpected, transactionsSorted);
    }

    @Test
     public void collect_all_the_unique_cities_where_traders_work(){

        List<String> resultExpected = Arrays.asList("Cambridge", "Milan");

        List<String> uniqueCities = transactions.stream()
                                                .map(Transaction::getTraderCity)
                                                .distinct()
                                                .collect(Collectors.toList());

        assertEquals(resultExpected, uniqueCities);
    }

    @Test
    public void collect_all_traders_from_cambridge_and_sort_them_by_name(){

        List<Trader> resultExpected = Arrays.asList(alan, brian, raoul);

        List<Trader> traderFromCambridgeSortedByName = transactions.stream()
                                .filter(transaction -> transaction.getTraderCity().equals("Cambridge"))
                                .map(Transaction::getTrader)
                                .sorted((trader1, trader2) -> trader1.getName().compareTo(trader2.getName()))
                                .distinct()
                                .collect(Collectors.toList());

        assertEquals(resultExpected, traderFromCambridgeSortedByName);
    }

    @Test
    public void collect_all_traders_name_as_a_string_sorted_alphabetically(){

        List<String> resultExpected = Arrays.asList("Alan", "Brian", "Mario", "Mario", "Raoul", "Raoul");

        List<String> allTraderNamesSortedAlphabetically = transactions.stream()
                                                                       .map(Transaction::getTraderName)
                                                                       .sorted(String::compareTo)
                                                                       .collect(Collectors.toList());

        assertEquals(resultExpected, allTraderNamesSortedAlphabetically);
    }

    @Test
    public void decide_whether_a_trader_is_based_in_Milan_or_not (){

        boolean thereIsATraderBasedInMilan = transactions.stream()
                                                         .map(Transaction::getTraderCity)
                                                         .anyMatch(s -> s.equals("Milan"));

        assertTrue(thereIsATraderBasedInMilan);
    }

    @Test
    public void collect_the_highest_value_of_all_transactions (){

        int maxValue = 1000;

        int resultValue = transactions.stream().map(Transaction::getValue).max(Integer::compareTo).orElse(0);

        assertEquals(maxValue, resultValue);
    }

}
