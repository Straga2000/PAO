package libUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class reader {

    private static Integer counter = 0;
    private Integer id;

    private String fullName;
    private List<historyLog> history;
    private List<historyLog> wishlist;

    private membership subscription = membership.getInstance();

    public reader(String fullName) {
        this.id = counter;
        this.fullName = fullName;
        counter++;
    }

    public reader(){
        this.id = counter;
        counter++;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<historyLog> getHistory() {
        return history;
    }

    public void setHistory(List<historyLog> history) {
        this.history = history;
    }

    public membership getSubscription() {
        return subscription;
    }

    public List<historyLog> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<historyLog> wishlist) {
        this.wishlist = wishlist;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        return "" + fullName + " can borrow " + subscription.getMaxBorrowed() + " from " +
                dateFormat.format(subscription.getStartMembership()) + " to " +
                dateFormat.format(subscription.getFinishMembership()) + " at a cost of " +
                subscription.getPriceMembership() + "$.";
    }

    public String toCSVFormat()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        ///name,max borrowed,start membership,finish membership,price,is VIP,pay date
        return "" + fullName + "," +subscription.getMaxBorrowed() + "," + subscription.getStartMembership() + "," +
               dateFormat.format(subscription.getFinishMembership()) + "," + dateFormat.format(subscription.getPriceMembership())
               + " " + subscription.getVIP() + " " + dateFormat.format(subscription.getPayDate()) + "\n";
    }

    public void setAll(Date startMembership, Date finishMembership, Integer maxBorrowed, Integer priceMembership,
                       Boolean isVIP, Date payDate) {
        subscription.setAll(startMembership, finishMembership, maxBorrowed, priceMembership, isVIP, payDate);
    }

    private Integer[] chooseMembership(Integer typeMembership) throws Exception {
        int maxBorrowed, priceMembership;

        switch (typeMembership)
        {
            case 1:
                maxBorrowed = 3;
                priceMembership = 20;
                break;
            case 2:
                maxBorrowed = 5;
                priceMembership = 40;
                break;
            case 3:
                maxBorrowed = 10;
                priceMembership = 50;
                break;
            default:
                throw new Exception("There is no subscription of this type");
        }

        return new Integer[]{maxBorrowed, priceMembership};
    }

    public membership createSubscription(Integer typeMembership, Boolean isVIP) throws Exception {

        membership.getInstance();
        Integer[] pair = chooseMembership(typeMembership);

        Date dateStart = new Date();

        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.add(Calendar.MONTH, 1);

        Date dateFinish = calendar.getTime();

        //setAll(Date startMembership, Date finishMembership, Integer maxBorrowed, Integer priceMembership,Boolean isVIP, Date payDate)
        subscription.setAll(dateStart, dateFinish, pair[0], pair[1], isVIP, dateFinish);
        return subscription;
    }

    public membership upgradeSubscription(Integer typeMembership, Boolean isVIP) throws Exception {

        try{
            Integer[] pair = chooseMembership(typeMembership);

            Date dateStart = this.subscription.getStartMembership();
            Date dateFinish = this.subscription.getFinishMembership();

            subscription.setAll(dateStart, dateFinish, pair[0], pair[1], isVIP, dateFinish);
            return subscription;
        }
        catch (Exception e) {
            return createSubscription(typeMembership, isVIP);
        }
    }

    public void cancelSubscription()
    {
        this.subscription = null;
    }

    public void addToWishlist(historyLog newLog)
    {
        wishlist.add(newLog);
    }

    public Boolean isSubscriptionNeededToPaid()
    {
        try {
            return subscription.getPayDate().before(new Date());
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<historyLog> addBookToHistory(book newBook)
    {
         historyLog historyObj = new historyLog(new Date(), newBook);
         history.add(historyObj);

         return history;
    }
}

class historyLog{

    private Date borrowedDate;
    private book borrowedBook;

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public historyLog(Date borrowedDate, book borrowedBook) {
        this.borrowedDate = borrowedDate;
        this.borrowedBook = borrowedBook;
    }
}

class membership
{

    private static membership instance;

    private Date startMembership;
    private Date finishMembership;
    private Integer maxBorrowed;
    private Integer priceMembership;
    private Boolean isVIP;
    private Date payDate;

    public static membership getInstance()
    {
        if(instance == null)    {
            instance = new membership();
        }
        return instance;
    }

    private membership() {
        setAll(null, null,0, 0, Boolean.FALSE, null);
    }


    public void setAll(Date startMembership, Date finishMembership, Integer maxBorrowed, Integer priceMembership,
                       Boolean isVIP, Date payDate){
        this.startMembership = startMembership;
        this.finishMembership = finishMembership;
        this.maxBorrowed = maxBorrowed;
        this.priceMembership = priceMembership;
        this.isVIP = isVIP;

        if(payDate != null)
            this.payDate = payDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getStartMembership() {
        return startMembership;
    }

    public Date getFinishMembership() {
        return finishMembership;
    }

    public Integer getMaxBorrowed() {
        return maxBorrowed;
    }

    public Integer getPriceMembership() {
        return priceMembership;
    }

    public Boolean getVIP() {
        return isVIP;
    }

    public void setMaxBorrowed(Integer maxBorrowed) {
        this.maxBorrowed = maxBorrowed;
    }

    public void setPriceMembership(Integer priceMembership) {
        this.priceMembership = priceMembership;
    }

    public void setVIP(Boolean isVIP) {
        this.isVIP = isVIP;
    }

}