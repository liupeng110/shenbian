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

@interface CHGlobalDataHelper()<CLLocationManagerDelegate>

@property(nonatomic,strong)CLLocationManager *locationManager;
@property(nonatomic,copy)CaculateReuslt caculateResult;
@property(nonatomic,strong)CLLocation *outLocation;
@property(nonatomic,copy)GetCurrentLocation currentLocation;
@property(nonatomic,assign)CHLoctionType loctionType;
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
        _locationManager = [[CLLocationManager alloc]init];
        _locationManager.delegate = self;
        
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

-(void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray<CLLocation *> *)locations{
    [_locationManager stopUpdatingLocation];
    /*旧值*/
    CLLocation * currentLocation = [locations lastObject];
    if (self.loctionType == CHLoctionTypeDistance) {
        
        double distance = [currentLocation distanceFromLocation:self.outLocation];
        if (distance < 1000) {
            NSString *distanceResult = [NSString stringWithFormat:@"%.f 米",distance];
            self.caculateResult(distanceResult);
        } else {
            NSString *distanceResult = [NSString stringWithFormat:@"%.1f 千米",distance/ 1000];
            self.caculateResult(distanceResult);
        }
    } else if (self.loctionType == CHLoctionTypeCurrentLocation){
        self.currentLocation(currentLocation);
        
    }
}

-(void)getCurrentLocation:(void (^)(CLLocation *location))complted
{
    self.currentLocation = complted;
    self.loctionType = CHLoctionTypeCurrentLocation;
    [self.locationManager startUpdatingLocation];

}

- (void)locateAction
{
    //带逆地理的单次定位
//    [self.locationManager requestLocationWithReGeocode:YES completionBlock:^(CLLocation *location, AMapLocationReGeocode *regeocode, NSError *error) {
//
//        if (error)
//        {
//            NSLog(@"locError:{%ld - %@};", (long)error.code, error.localizedDescription);
//
//            if (error.code == AMapLocationErrorLocateFailed)
//            {
//                return;
//            }
//        }
//
//        //定位信息
//        NSLog(@"location:%@", location);
//
//        //逆地理信息
//        if (regeocode)
//        {
//            NSLog(@"reGeocode:%@", regeocode);
//        }
//    }];
    
}

@end
