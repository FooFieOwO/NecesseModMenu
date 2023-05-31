package modmenu.client.menu;

public enum CurrentMenu {

    Main("Main", ""),
    Player("Player", ""),
    Server("Server", ""),
    Misc("Misc", "");

    public String name;
    public String description;

    CurrentMenu(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
}
