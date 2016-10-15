# mystic-ents
2-dimensional, turn based board game. The gameplay is win/lose (as in chess) and involves players capturing each other’s pieces. 

Mystic Ents is a turn based, top-down base capture game for up to 2-4 teams.

The goal of the game is for a player to use their character pieces (‘Pieces’) to capture an opponent's base while preventing capture of their own. The game occurs within a top-down view of a topological grid. The game map size is determined by the map data loaded during game startup.

Board Squares may contain:
- Team Base
- Inaccessible Wall/Barrier
- Player
- Team Usurper (Grim Reaper icon)

Each player commands a team of character pieces, generated procedurally with domised attribute traits (‘Traits’) that dictate their health, attack and movement range. One of the player’s pieces is called an ‘Usurper’, which is a decorated regular, which is
the only piece that is able to capture their opponent’s base.

All pieces are generated with one randomised Skill, a special ability that helps them perform alongside moving and attacking.

# Requirements
Java 1.8
