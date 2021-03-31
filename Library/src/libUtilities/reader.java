package libUtilities;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class reader {

    private static Integer counter = 0;
    private Integer id;

    private String fullName;
    private membership subscription;
    private List<historyLog> history;
    private List<historyLog> wishlist;

    public reader(String fullName, membership subscription) {
        this.id = counter;
        this.fullName = fullName;
        this.subscription = subscription;

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

    public void setSubscription(membership subscription) {
        this.subscription = subscription;
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
        return fullName + " can borrow " + subscription.getMaxBorrowed() + " from " + subscription.getStartMembership()
                + " to " + subscription.getFinishMemebership() + " at a cost of " + subscription.getPriceMembership() + "$.";
    }

    public membership createSubscription(Integer typeMembership, Boolean isVIP) throws Exception {
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

        Date dateStart = new Date();

        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.add(Calendar.MONTH, 1);

        Date dateFinish = calendar.getTime();

        return new membership(dateStart, dateFinish, maxBorrowed, priceMembership, isVIP);
    }

    public membership upgradeSubscription(Integer typeMembership, Boolean isVIP) throws Exception {
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

        Date dateStart = this.subscription.getStartMembership();
        Date dateFinish = this.subscription.getFinishMemebership();

        return new membership(dateStart, dateFinish, maxBorrowed, priceMembership, isVIP);
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
           return subscription.getPayDate().before(new Date());
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
    private Date startMembership;
    private Date finishMemebership;
    private Integer maxBorrowed;
    private Integer priceMembership;
    private Boolean isVIP;
    private Date payDate;

    public membership()
    {

        startMembership = new Date();

        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(startMembership);
        calendar.add(Calendar.MONTH, 1);

        finishMemebership = calendar.getTime();
        payDate = finishMemebership;

        maxBorrowed = 0;
        priceMembership = 0;
        isVIP = Boolean.FALSE;


    }

    public membership(Date startMembership, Date finishMemebership, Integer maxBorrowed, Integer priceMembership, Boolean isVIP) {
        this.startMembership = startMembership;
        this.finishMemebership = finishMemebership;
        this.maxBorrowed = maxBorrowed;
        this.priceMembership = priceMembership;
        this.isVIP = isVIP;
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

    public Date getFinishMemebership() {
        return finishMemebership;
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

    public void setVIP(Boolean VIP) {
        isVIP = VIP;
    }
}