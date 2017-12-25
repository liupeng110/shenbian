//
//  CHCardsPanelView.h
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017 helinkeji. All rights reserved.
//

typedef NS_ENUM(NSInteger,CHFindType) {
    CHFindTypeService,
    CHFindTypeFindPeople,
    CHFindTypeSearchJob,
    CHFindTypeFindEvents,
    CHFindTypeRentHouse,
    CHFindTypeLearnSkills,
    CHFindTypeMaintenance,
    CHFindTypeAll
};


typedef void(^SelectFindType)(CHFindType type);

#import <UIKit/UIKit.h>

#import "CHCardsPanelModel.h"

@interface CHCardsPanelView : UIView
@property (nonatomic,strong) CHCardsPanelModel *pannelModel;
@property (nonatomic,copy) SelectFindType selectFindType;
@property (nonatomic,copy)NSArray *categoryItemList;

@end
