package com.techelevator.locations.controllers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {
    @RequestMapping(path = "/locations", method = RequestMethod.GET)
    public List<Location> list() {
        return locations;
    }
    @RequestMapping(path = "/locations", method = RequestMethod.POST)
    public Location add(@RequestBody Location location) {
        if (location != null) {
            locations.add(location);
            return location;
        }
        return null;
    }
    private List<Location> locations = new ArrayList<>();

    public LocationController() {
        locations.add(new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        });
        locations.add(new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        });
        locations.add(new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        });
        locations.add(new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        });
        locations.add(new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        });
        locations.add(new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        });

        }
    }

