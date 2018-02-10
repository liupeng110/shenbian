//
//  CHGlobalDataHelper.m
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017 . All rights reserved.
//

#import "CHGlobalDataHelper.h"
#import <CoreLocation/CoreLocation.h>
typedef  NS_ENUM(NSInteger,CHLoctionType){
    CHLoctionTypeDistance,
    CHLoctionTypeCurrentLocation,
};

@interface CHGlobalDataHelper()<AMapLocationManagerDelegate>

@property(nonatomic,copy)CaculateReuslt caculateResult;
@property(nonatomic,strong)CLLocation *outLocation;
@property(nonatomic,copy)GetCurrentLocation getLocation;
@property(nonatomic,assign)CHLoctionType loctionType;
@property(nonatomic,strong)CLLocation *location;

@end

@implementation CHGlobalDataHelper

+ (CHGlobalDataHelper*)shareInstance{
    
    static CHGlobalDataHelper *instance = nil;
    static dispatch_once_t onceToken ;
    dispatch_once(&onceToken, ^{
        instance = [[CHGlobalDataHelper alloc]init];
    });
    return instance;
}

-(instancetype)init{
    if (self = [super init]) {
        _locationManager = [[AMapLocationManager alloc]init];
        _locationManager.delegate = self;
        [_locationManager setLocatingWithReGeocode:YES];
        [_locationManager startUpdatingLocation];

    }
    return self;
}


-(void)distacewithLocation:(NSString *)locationStr result:(void (^)(NSString*))caculateResult{
    NSArray *locations = [locationStr componentsSeparatedByString:@","];
    CLLocationDegrees longitude = [locations.firstObject doubleValue];
    CLLocationDegrees latitude = [locations[1] doubleValue];
    CLLocation *location = [[CLLocation alloc]initWithLatitude:(latitude) longitude:(longitude)];
    self.outLocation = location;
    self.caculateResult = caculateResult;
    self.loctionType = CHLoctionTypeDistance;
    [self.locationManager startUpdatingLocation];
 
}

-(void)amapLocationManager:(AMapLocationManager *)manager didUpdateLocation:(CLLocation *)location reGeocode:(AMapLocationReGeocode *)reGeocode{
    
    /*旧值*/
    self.location = location;
    if (self.loctionType == CHLoctionTypeDistance && self.caculateResult) {
        
        double distance = [location distanceFromLocation:self.outLocation];
        if (distance < 1000) {
            NSString *distanceResult = [NSString stringWithFormat:@"%.f 米",distance];
            self.caculateResult(distanceResult);
            
        } else {
            NSString *distanceResult = [NSString stringWithFormat:@"%.1f 千米",distance/ 1000];
            self.caculateResult(distanceResult);
        }
    } else if (self.loctionType == CHLoctionTypeCurrentLocation && self.getLocation){
        self.getLocation(location);
    }
    
    //逆地理信息
    if (reGeocode)
    {
        NSLog(@"reGeocode:%@", reGeocode);
        self.currentCity = reGeocode.city;
        NSString *locationStr = [NSString stringWithFormat:@"%f,%f",location.coordinate.longitude,location.coordinate.latitude];
        if(![locationStr isEqualToString:self.currentLocation ]){
            self.currentLocation = locationStr;
        }
        [self.locationManager stopUpdatingLocation];

    }
    
}




-(void)getCurrentLocation:(void (^)(CLLocation *location))complted
{
    self.getLocation = complted;
    self.loctionType = CHLoctionTypeCurrentLocation;
    [self.locationManager startUpdatingLocation];

}

@end
